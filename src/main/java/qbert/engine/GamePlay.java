package qbert.engine;

import qbert.characters.Move;
import qbert.characters.Player;
import qbert.level.Block;
import qbert.level.Level;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class GamePlay {

    private static GamePlay instance = null;
    private static final int MOVE_UP_KEY = KeyEvent.VK_ALT;
    private static final int MOVE_UP_MODIFIER = InputEvent.ALT_DOWN_MASK;

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

    public boolean blockReachableByNextMove(Block block) {
        if (!initialised) {
            return false;
        }

        Block playerBlock = player.getPosition();
        if (Engine.getInstance().getKeyboardHandler().isKeyDown(MOVE_UP_KEY)) {
            Block nul = playerBlock.getNeighborUpperLeft();
            Block nur = playerBlock.getNeighborUpperRight();
            if ((nul != null && nul.equals(block)) || (nur != null && nur.equals(block))) {
                return true;
            }
        } else {
            Block nll = playerBlock.getNeighborLowerLeft();
            Block nlr = playerBlock.getNeighborLowerRight();
            if ((nll != null && nll.equals(block)) || (nlr != null && nlr.equals(block))) {
                return true;
            }
        }
        return false;
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
        kbh.addKeyboardAction(KeyEvent.VK_LEFT, MOVE_UP_MODIFIER, ae -> { player.move(Move.UPPER_LEFT); canvas.repaint(); });
        kbh.addKeyboardAction(KeyEvent.VK_RIGHT, MOVE_UP_MODIFIER, ae -> { player.move(Move.UPPER_RIGHT); canvas.repaint(); });
    }

}
