package qbert.engine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class KeyboardHandler {

    private static KeyboardHandler instance = null;
    private InputMap inputMap;
    private ActionMap actionMap;

    private KeyboardHandler(Canvas canvas) {
        this.inputMap = canvas.getInputMap();
        this.actionMap = canvas.getActionMap();
    }

    public static KeyboardHandler getInstance(Canvas canvas) {
        if (instance == null) {
            instance = new KeyboardHandler(canvas);
        }

        return instance;
    }

    public void addKeyboardAction(int keyCode, ActionListener action) {
        addKeyboardAction(KeyStroke.getKeyStroke(keyCode, 0), action);
    }

    public void addKeyboardAction(KeyStroke keyStroke, ActionListener action) {
        String key = UUID.randomUUID().toString();
        inputMap.put(keyStroke, key);
        actionMap.put(key, new EventDispatcher(action));
    }

    static private class EventDispatcher extends AbstractAction {

        ActionListener dispatcher;

        EventDispatcher(final ActionListener dispatch) {
            this.dispatcher = dispatch;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.dispatcher.actionPerformed(e);
        }
    }

}
