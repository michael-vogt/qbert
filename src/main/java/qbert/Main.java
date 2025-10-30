package qbert;

import qbert.engine.Engine;
import qbert.level.LevelReader;

import javax.swing.*;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        URL url = LevelReader.class.getResource("/level/level01.xml");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Engine.getInstance().loadLevel(url);
            }
        });

    }
}