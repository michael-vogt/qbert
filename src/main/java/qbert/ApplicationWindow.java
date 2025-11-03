package qbert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class ApplicationWindow extends JFrame {

    private static final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private static final String TITLE = "QBert";

    public ApplicationWindow() {
        this(TITLE);
    }

    public ApplicationWindow(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void toggleFullscreen() {
        if (device.getFullScreenWindow() == null) {
            device.setFullScreenWindow(this);
        } else {
            device.setFullScreenWindow(null);

        }
    }


}
