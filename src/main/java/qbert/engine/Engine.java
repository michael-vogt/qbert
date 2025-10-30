package qbert.engine;

import qbert.ApplicationWindow;
import qbert.level.Level;
import qbert.level.LevelReader;

import java.awt.event.KeyEvent;
import java.net.URL;

public class Engine {

    private static Engine instance = null;

    private final ApplicationWindow applicationWindow;
    private final Canvas canvas;
    private final KeyboardHandler keyboardHandler;

    private Engine() {
        canvas = new Canvas();
        applicationWindow = new ApplicationWindow();

        canvas.setParent(applicationWindow);
        applicationWindow.setContentPane(canvas);

        keyboardHandler = KeyboardHandler.getInstance(canvas.getInputMap(), canvas.getActionMap());
        keyboardHandler.addKeyboardAction(KeyEvent.VK_ESCAPE, ae -> applicationWindow.close());
        keyboardHandler.addKeyboardAction(KeyEvent.VK_F11, ae -> applicationWindow.toggleFullscreen());
    }

    public static Engine getInstance() {
        if (instance == null) {
            instance = new Engine();
        }

        return instance;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public KeyboardHandler getKeyboardHandler() {
        return keyboardHandler;
    }

    public void loadLevel(URL url) {
        Level level = LevelReader.read(url);
        GamePlay.getInstance().initialise(level);
        canvas.setLevel(level);
    }

}
