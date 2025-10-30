package qbert.graphicsengine;

import qbert.level.Level;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    Level level;

    public Canvas(Level level) {
        this.level = level;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        defineRenderingHints(g2d);

        Drawer.drawLevel(g2d, level, this.getWidth() / 2, 100);
    }

    private void defineRenderingHints(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

}
