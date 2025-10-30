package qbert.engine;

import qbert.characters.CharacterState;
import qbert.characters.Player;

import java.awt.*;

public class Colors {

    public static Color CubeTop = new Color(200, 200, 200);
    public static Color CubeFront = new Color(129, 129, 129);
    public static Color CubeSide = new Color(182, 182, 182);
    public static Color CubeHighlight = new Color(0, 0, 255, 64);
    public static Color PlayerAlive = new Color(255, 128, 0);
    public static Color PlayerDead = new Color(255, 128, 0, 128);

    public static Color getPlayerColor(Player player) {
        if (player != null) {
            return (player.isState(CharacterState.ALIVE) ? PlayerAlive : PlayerDead);
        }

        return null;
    }

}
