package qbert.engine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class KeyboardHandler {

    private static KeyboardHandler instance = null;
    private final InputMap inputMap;
    private final ActionMap actionMap;

    private KeyboardHandler(InputMap inputMap, ActionMap actionMap) {
        this.inputMap = inputMap;
        this.actionMap = actionMap;
    }

    public static KeyboardHandler getInstance(InputMap inputMap, ActionMap actionMap) {
        if (instance == null) {
            instance = new KeyboardHandler(inputMap, actionMap);
        }

        return instance;
    }

    public void addKeyboardAction(int keyCode, ActionListener action) {
        addKeyboardAction(KeyStroke.getKeyStroke(keyCode, 0), action);
    }

    public void addKeyboardAction(int keyCode, int modifiers, ActionListener action) {
        addKeyboardAction(KeyStroke.getKeyStroke(keyCode, modifiers), action);
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
