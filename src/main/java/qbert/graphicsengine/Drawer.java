package qbert.graphicsengine;

import qbert.level.Block;
import qbert.level.Level;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Drawer {

    private Drawer() {}

    public static void drawLevel(Graphics2D g2d, Level level, int startX, int startY) {
        int size = 50;
        double alpha = Math.toRadians(20);

        double dx = size * Math.cos(alpha);
        double dy = size * Math.sin(alpha);

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

    public static void drawCubes(Graphics2D g2d) {
        int size = 100;
        double alpha = Math.toRadians(30);

        double dx = size * Math.cos(alpha);
        double dy = size * Math.sin(alpha);

        g2d.translate(200, 100);
        drawCube(g2d, size, alpha);
        g2d.translate(-dx, size + dy);
        drawCube(g2d, size, alpha);
        g2d.translate(2*dx, 0);
        drawCube(g2d, size, alpha);
    }

    private static void drawCube(Graphics2D g2d, int size, double alpha) {
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

    private static void printTransformationMatrix(AffineTransform T) {
        double[] matrix = new double[6];
        T.getMatrix(matrix);
        StringBuilder sb = new StringBuilder();
        sb.append("[\n ");
        for (int i = 0; i < 2; i++) {
            sb.append("[");
            for (int j = 0; j < 3; j++) {
                sb.append(String.format("%.2f ", matrix[3*i+j]));
            }
            sb.append("]\n");
        }
        System.out.println(sb.append("]"));
    }

}
