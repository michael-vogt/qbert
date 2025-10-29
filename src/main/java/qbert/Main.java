package qbert;

import qbert.level.Level;
import qbert.level.LevelReader;

import javax.swing.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        URL url = LevelReader.class.getResource("/level/level01.xml");
        Level level = LevelReader.read(url);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ApplicationWindow("qbert", level);
            }
        });

    }
}