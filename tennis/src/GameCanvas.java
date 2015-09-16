/**
 * User: celentano
 * Date: 10.09.15.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameCanvas extends Canvas implements Game, Runnable {
    private Thread gameThread;
    private AtomicBoolean running;
    private Input input;
    private Scene scene;
    private MainFrame frame;
    private Timer endTimer;
    private boolean endFlag = false;

    public GameCanvas(Dimension screenSize) {
        running = new AtomicBoolean(false);
        setSize(screenSize);
        initInput();
        initFocusListener();
        endTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (endFlag) {
                    if (frame != null) {
                        running.set(false);
                        try {
                            gameThread.join();
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                        frame.dispose();
                        System.exit(0);
                    }
                }
            }
        });
        endTimer.start();
    }

    public void setFrame(MainFrame frame) {
        this.frame = frame;
    }

    private void initInput() {
        input = new Input();
        addKeyListener(input);
    }

    private void initFocusListener() {
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent event) {
                start();
            }

            @Override
            public void focusLost(FocusEvent event) {
                pause();
            }
        });
    }

    @Override
    public void start() {
        if (running.compareAndSet(false, true)) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void pause() {
        if (running.compareAndSet(true, false)) {
            try {
                gameThread.join();
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

    @Override
    public void stop() {
        endFlag = true;
    }

    @Override
    public Dimension getScreenSize() {
        return getSize();
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void run() {
        long previousIterationTime = System.nanoTime();
        while (running.get()) {
            if (scene == null) {
                continue;
            }
            long now = System.nanoTime();
            long nanosPassed = now - previousIterationTime;
            previousIterationTime = now;
            Graphics2D g = (Graphics2D) getBufferStrategy().getDrawGraphics();
            scene.update(nanosPassed);
            scene.draw(g);
            getBufferStrategy().show();
            Toolkit.getDefaultToolkit().sync();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
