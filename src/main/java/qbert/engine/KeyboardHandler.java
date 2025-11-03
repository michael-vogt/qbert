package qbert.engine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KeyboardHandler implements KeyListener {

    private static KeyboardHandler instance = null;
    private static boolean shiftDown = false;
    private final InputMap inputMap;
    private final ActionMap actionMap;
    private static Map<Integer, Boolean> keyDown = new HashMap<>();

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

    public boolean isKeyDown(int keyCode) {
        return keyDown.get(keyCode) == Boolean.TRUE;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (!keyDown.containsKey(keyCode)) {
            keyDown.put(keyCode, true);
        } else if (!keyDown.get(keyCode)) {
            keyDown.put(keyCode, true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (!keyDown.containsKey(keyCode)) {
            keyDown.put(keyCode, false);
        } else if (keyDown.get(keyCode)) {
            keyDown.put(keyCode, false);
        }
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
