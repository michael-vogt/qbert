package qbert;

import qbert.engine.Engine;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Engine.getInstance();
            }
        });

    }
}