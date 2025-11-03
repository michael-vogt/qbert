package qbert.engine;

import qbert.characters.Player;
import qbert.level.Block;
import qbert.level.Level;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.List;

public class Drawer {

    private static Drawer instance = null;
    private final Canvas canvas;

    private AffineTransform T_nextRow = new AffineTransform();
    private AffineTransform T_prevRow = new AffineTransform();
    private AffineTransform T_nextColumn = new AffineTransform();
    private AffineTransform T_prevColumn = new AffineTransform();

    private Drawer(Canvas canvas) {
        this.canvas = canvas;
    }

    public static Drawer getInstance(Canvas canvas) {
        if (instance == null) {
            instance = new Drawer(canvas);
        }

        return instance;
    }

    public void drawLevel(Graphics2D g2d, Level level) {
        int size = 50;
        double alpha = Math.toRadians(20);

        double dx = size * Math.cos(alpha);
        double dy = size * Math.sin(alpha);

        Dimension levelDimensionInRowsAndColumns = level.getDimension();
        Dimension levelDimensionInPixel = new Dimension((int) (2 * dx * levelDimensionInRowsAndColumns.width), (int) (levelDimensionInRowsAndColumns.height * size + 2 * dy * (Math.ceilDiv(levelDimensionInRowsAndColumns.height, 2))));
        Dimension canvasSize = new Dimension(canvas.getWidth(), canvas.getHeight());
        int startX = canvasSize.width / 2;
        int startY = (canvasSize.height - levelDimensionInPixel.height) / 2 + (int)dy;

        setTransformations(size, alpha);

        AffineTransform T0 = g2d.getTransform();
        T0.translate(startX, startY);

        List<Block> blocks = level.getBlocks();
        for (Block block : blocks) {
            int row = block.getRow();
            int column = block.getColumn();

            moveToRowColumn(g2d, T0, row, column);

            drawCube(g2d, size, alpha, null);

            drawCube(g2d, size, alpha, Colors.getCubeShadingColor(block));

            /*if (GamePlay.getInstance().blockReachableByNextMove(block)) {
                drawCube(g2d, size, alpha, Colors.CubeReachable);
            }
            if (block.getNumberOfVisits() > 0) {
                drawCube(g2d, size, alpha, Colors.CubeHighlight);
            }*/

        }

        g2d.setTransform(T0);
    }

    public void drawPlayer(Graphics2D g2d, Player player) {
        double size = 40;

        Block position = player.getPosition();
        moveToRowColumn(g2d, g2d.getTransform(), position.getRow(), position.getColumn());
        g2d.setColor(Colors.getPlayerColor(player));

        g2d.translate(-size / 2, -(2.0/3.0)*size);

        g2d.fillOval(0, 0, (int) size, (int) size);
    }

    private void drawCube(Graphics2D g2d, int size, double alpha, Color highlightColor) {
        AffineTransform originalTransform = g2d.getTransform();

        double dx = size * Math.cos(alpha);
        double dy = size * Math.sin(alpha);
        Path2D diamond = new Path2D.Double();
        diamond.moveTo(0, -dy);
        diamond.lineTo(dx, 0);
        diamond.lineTo(0, dy);
        diamond.lineTo(-dx, 0);
        diamond.closePath();

        g2d.setColor((highlightColor == null) ? Colors.CubeTop : highlightColor);
        g2d.fill(diamond);

        diamond = new Path2D.Double();
        diamond.moveTo(dx, 0);
        diamond.lineTo(dx, size);
        diamond.lineTo(0, dy + size);
        diamond.lineTo(0, dy);
        diamond.closePath();

        g2d.setColor((highlightColor == null) ? Colors.CubeSide : highlightColor);
        g2d.fill(diamond);

        diamond = new Path2D.Double();
        diamond.moveTo(0, dy);
        diamond.lineTo(0, dy + size);
        diamond.lineTo(-dx, size);
        diamond.lineTo(-dx, 0);
        diamond.closePath();

        g2d.setColor((highlightColor == null) ? Colors.CubeFront : highlightColor);
        g2d.fill(diamond);

        g2d.setTransform(originalTransform);
    }

    private void moveToRowColumn(Graphics2D g2d, AffineTransform T0, int row, int column) {
        AffineTransform T_row = (row > 0) ? T_nextRow : T_prevRow;
        AffineTransform T_column = (column > 0) ? T_nextColumn : T_prevColumn;

        g2d.setTransform(T0);
        for (int i = 0; i < Math.abs(row); i++) {
            g2d.transform(T_row);
        }
        for (int j = 0; j < Math.abs(column); j++) {
            g2d.transform(T_column);
        }
    }

    private void setTransformations(int size, double alpha) {
        double dx = size * Math.cos(alpha);
        double dy = size * Math.sin(alpha);

        T_nextRow.setToTranslation(-dx, size + dy);
        T_prevRow.setToTranslation(dx, -(size + dy));
        T_nextColumn.setToTranslation(2 * dx, 0);
        T_prevColumn.setToTranslation(-2 * dx, 0);
    }

}
