package qbert.engine;

import qbert.ApplicationWindow;
import qbert.exceptions.EngineNotInitializedException;
import qbert.level.Level;
import qbert.level.LevelReader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Engine {

    private static Engine instance = null;

    private static final URL urlLevel1 = LevelReader.class.getResource("/level/level01.xml");
    private static final URL urlLevel2 = LevelReader.class.getResource("/level/level02.xml");

    private ApplicationWindow applicationWindow;
    private Canvas canvas;
    private KeyboardHandler keyboardHandler;
    private boolean initialized = false;

    private Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
    private boolean antialiasing;

    private Engine() {
    }

    public static Engine getInstance() {
        if (instance == null) {
            instance = new Engine();
        }

        return instance;
    }

    public void initialize() {
        initialized = true;
        canvas = new Canvas();
        applicationWindow = new ApplicationWindow();

        canvas.setParent(applicationWindow);
        applicationWindow.setContentPane(canvas);

        keyboardHandler = KeyboardHandler.getInstance(canvas.getInputMap(), canvas.getActionMap());
        keyboardHandler.addKeyboardAction(KeyEvent.VK_ESCAPE, ae -> applicationWindow.close());
        keyboardHandler.addKeyboardAction(KeyEvent.VK_F11, ae -> applicationWindow.toggleFullscreen());

        keyboardHandler.addKeyboardAction(KeyEvent.VK_F1, ae -> { loadLevel(urlLevel1); canvas.repaint(); } );
        keyboardHandler.addKeyboardAction(KeyEvent.VK_F2, ae -> { loadLevel(urlLevel2); canvas.repaint(); } );
        keyboardHandler.addKeyboardAction(KeyEvent.VK_F3, ae -> { toggleAntialiasing(); canvas.repaint(); } );

        canvas.addKeyListener(keyboardHandler);
        setAntialiasing(true);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public KeyboardHandler getKeyboardHandler() {
        return keyboardHandler;
    }

    public Map<RenderingHints.Key, Object> getRenderingHints() {
        return renderingHints;
    }

    public void loadLevel(URL url) {
        if (!initialized) {
            throw new EngineNotInitializedException("Engine not initialized!");
        }
        Level level = LevelReader.read(url);
        GamePlay.getInstance().initialise(level);
        canvas.setLevel(level);
    }

    public void setFPS(int fps) {
        if (initialized) {
            canvas.setFPS(fps);
        }
    }

    public void startApplication() {
        if (!initialized) {
            throw new EngineNotInitializedException("Engine not initialized!");
        }
        applicationWindow.setVisible(true);
    }

    public void toggleAntialiasing() {
        setAntialiasing(!antialiasing);
    }

    private void setAntialiasing(boolean antialiasing) {
        this.antialiasing = antialiasing;
        if (antialiasing) {
            renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        } else {
            renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        }
    }

}
