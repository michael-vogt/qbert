package qbert;

import qbert.graphicsengine.Canvas;
import qbert.level.Level;

import javax.swing.*;

public class ApplicationWindow extends JFrame {

    public ApplicationWindow(String title, Level level) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        Canvas contentPane = new Canvas(level);
        setContentPane(contentPane);

        setVisible(true);
    }


}
