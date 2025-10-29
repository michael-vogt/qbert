package qbert;

import qbert.graphicsengine.Drawer;
import qbert.level.Level;

import javax.swing.*;
import java.awt.*;

public class ApplicationWindow extends JFrame {

    private JPanel contentPane;
    private Level level;
    
    public ApplicationWindow(String title, Level level) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.level = level;

        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Drawer.drawLevel((Graphics2D) g, level, this.getWidth()/2, 100);
            }
        };
        setContentPane(contentPane);

        setVisible(true);
    }


}
