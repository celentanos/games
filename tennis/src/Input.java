/**
 * Created by celentano on 10.09.15.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

public class Input implements KeyListener {
    private final Collection<KeyEvent> keyPressedEvents;
    private final Collection<KeyEvent> keyReleasedEvents;
    private KeyEvent key;
    private Timer timer;
    private AtomicBoolean firstPress;

    public Input() {
        keyPressedEvents = new ArrayList<KeyEvent>();
        keyReleasedEvents = new ArrayList<KeyEvent>();

        firstPress = new AtomicBoolean(false);

        // TODO: timer delay must be 100ms
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                keyPressedEvents.add(key);
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent event) {
    }

    @Override
    public synchronized void keyPressed(KeyEvent event) {
//        keyPressedEvents.add(event);
        if (firstPress.compareAndSet(false, true))
            keyPressedEvents.add(event);
        key = event;
        timer.start();
    }

    public synchronized Collection<KeyEvent> getKeyPressedEvents() {
        Collection<KeyEvent> events = new ArrayList<KeyEvent>(keyPressedEvents);
        keyPressedEvents.clear();
        return events;
    }

    @Override
    public synchronized void keyReleased(KeyEvent event) {
        keyReleasedEvents.add(event);
        timer.stop();
        firstPress.set(false);
    }

    public synchronized Collection<KeyEvent> getKeyReleasedEvents() {
        Collection<KeyEvent> events = new ArrayList<KeyEvent>(keyReleasedEvents);
        keyReleasedEvents.clear();
        return events;
    }
}

