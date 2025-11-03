package qbert.engine;

import qbert.characters.CharacterState;
import qbert.characters.Player;
import qbert.level.Block;

import java.awt.*;

public class Colors {

    public static Color CubeTop = new Color(200, 200, 200);
    public static Color CubeFront = new Color(129, 129, 129);
    public static Color CubeSide = new Color(182, 182, 182);
    public static Color CubeHighlight = new Color(0, 0, 255, 64);
    public static Color CubeReachable = new Color(255, 255, 0, 64);
    public static Color PlayerAlive = new Color(255, 128, 0);
    public static Color PlayerDead = new Color(255, 128, 0, 128);

    public static Color getCubeShadingColor(Block block) {
        if (block == null) {
            return null;
        }

        if (GamePlay.getInstance().blockReachableByNextMove(block)) {
            return CubeReachable;
        }

        if (block.getNumberOfVisits() > 0) {
            return CubeHighlight;
        }

        return null;
    }

    public static Color getPlayerColor(Player player) {
        if (player != null) {
            return (player.isState(CharacterState.ALIVE) ? PlayerAlive : PlayerDead);
        }

        return null;
    }

}
