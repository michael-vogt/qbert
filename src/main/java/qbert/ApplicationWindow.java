package qbert;

import qbert.engine.Canvas;
import qbert.level.Level;

import javax.swing.*;
import java.awt.*;

public class ApplicationWindow extends JFrame {

    private static final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    public ApplicationWindow(String title, Level level) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        Canvas contentPane = new Canvas(level, this);
        setContentPane(contentPane);

        setVisible(true);
    }

    public void toggleFullscreen() {
        if (device.getFullScreenWindow() == null) {
            device.setFullScreenWindow(this);
        } else {
            device.setFullScreenWindow(null);

        }
    }


}
