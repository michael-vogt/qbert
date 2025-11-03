package qbert;

import qbert.engine.Engine;

import javax.swing.*;

public class Main {

    public static final Engine engine = Engine.getInstance();

    public static void main(String[] args) {
        engine.initialize();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                engine.startApplication();
            }
        });

    }
}