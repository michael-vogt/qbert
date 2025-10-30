package qbert.engine;

import qbert.ApplicationWindow;
import qbert.level.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class Canvas extends JPanel {

    private final Level level;
    private final Drawer drawer;
    private final ApplicationWindow parent;

    public Canvas(Level level, ApplicationWindow parent) {
        this.level = level;
        this.parent = parent;
        this.drawer = Drawer.getInstance(this);
        defineKeyboardActions();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        defineRenderingHints(g2d);
        drawer.drawLevel(g2d, level);
    }

    private void defineRenderingHints(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    private void defineKeyboardActions() {
        InputMap inputMap = getInputMap();
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "VK_ESCAPE");
        actionMap.put("VK_ESCAPE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.dispatchEvent(new WindowEvent(parent, WindowEvent.WINDOW_CLOSING));
            }
        });

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), "VK_F11");
        actionMap.put("VK_F11", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.toggleFullscreen();
            }
        });

    }

}
