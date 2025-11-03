package qbert.engine;

import qbert.ApplicationWindow;
import qbert.level.Level;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Canvas extends JPanel {

    private Level level;
    private final Drawer drawer;
    private ApplicationWindow parent;
    private int fps = 1;
    private final Timer timer;

    public Canvas() {
        this(null, null);
    }

    private Canvas(Level level, ApplicationWindow parent) {
        this.level = level;
        this.parent = parent;
        this.drawer = Drawer.getInstance(this);

        this.timer = new Timer(1000 / fps, e -> {
           repaint();
        });
        timer.setRepeats(true);
        timer.start();
    }

    public void setFPS(int fps) {
        if (fps > 0) {
            this.fps = fps;
            timer.setDelay(1000 / fps);
        }
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setParent(ApplicationWindow parent) {
        this.parent = parent;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (level != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            setRenderingHints(g2d, Engine.getInstance().getRenderingHints());
            drawer.drawLevel(g2d, level);
            drawer.drawPlayer(g2d, GamePlay.getInstance().getPlayer());
        }
    }

    public void setRenderingHints(Graphics2D g2d, Map<RenderingHints.Key, Object> renderingHints) {
        renderingHints.forEach(g2d::setRenderingHint);
    }


}
