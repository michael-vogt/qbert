package qbert.engine;

import qbert.characters.Move;
import qbert.characters.Player;
import qbert.level.Level;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class GamePlay {

    private static GamePlay instance = null;

    private Level level;
    private Player player;
    private boolean initialised = false;

    private GamePlay() {
    }

    public static GamePlay getInstance() {
        if (instance == null) {
            instance = new GamePlay();
        }

        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public void initialise(Level level) {
        if (level != null) {
            this.level = level;
            player = new Player(level.getStartingBlock());
            initialiseKeyboard();
            initialised = true;
        }
    }

    public boolean isInitialised() {
        return initialised;
    }

    private void initialiseKeyboard() {
        KeyboardHandler kbh = Engine.getInstance().getKeyboardHandler();
        Canvas canvas = Engine.getInstance().getCanvas();

        kbh.addKeyboardAction(KeyEvent.VK_LEFT, ae -> { player.move(Move.LOWER_LEFT); canvas.repaint(); });
        kbh.addKeyboardAction(KeyEvent.VK_RIGHT, ae -> { player.move(Move.LOWER_RIGHT); canvas.repaint(); });
        kbh.addKeyboardAction(KeyEvent.VK_LEFT, InputEvent.SHIFT_DOWN_MASK, ae -> { player.move(Move.UPPER_LEFT); canvas.repaint(); });
        kbh.addKeyboardAction(KeyEvent.VK_RIGHT, InputEvent.SHIFT_DOWN_MASK, ae -> { player.move(Move.UPPER_RIGHT); canvas.repaint(); });
    }

}
