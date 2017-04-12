package source;

import javax.swing.*;

/**
 * Created by narey on 11.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminWindow();
                new MainWindow();
            }
        });
    }
}
