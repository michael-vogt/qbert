package qbert.engine;

import qbert.level.Block;
import qbert.level.Level;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.List;

public class Drawer {

    private static Drawer instance = null;
    private final Canvas canvas;

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

        AffineTransform T_nextRow = new AffineTransform();
        T_nextRow.translate(-dx, size + dy);
        AffineTransform T_prevRow = new AffineTransform();
        T_prevRow.translate(dx, -(size + dy));
        AffineTransform T_nextColumn = new AffineTransform();
        T_nextColumn.translate(2 * dx, 0);
        AffineTransform T_prevColumn = new AffineTransform();
        T_prevColumn.translate(-2 * dx, 0);

        AffineTransform T0 = g2d.getTransform();

        T0.translate(startX, startY);

        List<Block> blocks = level.getBlocks();
        for (Block block : blocks) {
            int row = block.getRow();
            int column = block.getColumn();

            AffineTransform T_row = (row > 0) ? T_nextRow : T_prevRow;
            AffineTransform T_column = (column > 0) ? T_nextColumn : T_prevColumn;

            g2d.setTransform(T0);
            for (int i = 0; i < Math.abs(row); i++) {
                g2d.transform(T_row);
            }
            for (int j = 0; j < Math.abs(column); j++) {
                g2d.transform(T_column);
            }

            drawCube(g2d, size, alpha);
            g2d.drawString(String.valueOf(block.getId()), 0, 0);

        }


    }

    private void drawCube(Graphics2D g2d, int size, double alpha) {
        AffineTransform originalTransform = g2d.getTransform();

        double dx = size * Math.cos(alpha);
        double dy = size * Math.sin(alpha);
        Path2D diamond = new Path2D.Double();
        diamond.moveTo(0, -dy);
        diamond.lineTo(dx, 0);
        diamond.lineTo(0, dy);
        diamond.lineTo(-dx, 0);
        diamond.closePath();

        //g2d.translate(x, y);
        g2d.setColor(Colors.CubeTop);
        g2d.fill(diamond);

        diamond = new Path2D.Double();
        diamond.moveTo(dx, 0);
        diamond.lineTo(dx, size);
        diamond.lineTo(0, dy + size);
        diamond.lineTo(0, dy);
        diamond.closePath();

        g2d.setColor(Colors.CubeSide);
        g2d.fill(diamond);

        diamond = new Path2D.Double();
        diamond.moveTo(0, dy);
        diamond.lineTo(0, dy + size);
        diamond.lineTo(-dx, size);
        diamond.lineTo(-dx, 0);
        diamond.closePath();

        g2d.setColor(Colors.CubeFront);
        g2d.fill(diamond);

        g2d.setTransform(originalTransform);
    }

}
