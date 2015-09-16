/**
 * Created by celentano on 10.09.15.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DesktopGameBuilder {
    public static Game build(Dimension screenSize) {
        final GameCanvas game = new GameCanvas(screenSize);
        MainFrame frame = new MainFrame();
        game.setFrame(frame);
        frame.setFocusable(false);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        game.createBufferStrategy(2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                game.pause();
            }
        });
        frame.requestFocus();
        game.requestFocus();
        return game;
    }
}

