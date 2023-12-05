package src.WindowComponent;
import javax.swing.JFrame;

import java.awt.Component;

public class Window extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public static final int PROJECT_WIDTH = 800;
    public static final int PROJECT_HEIGHT = 600;


    public Window() {
        super("Paint--");
        setSize(WIDTH,HEIGHT);

        paint p = new paint(PROJECT_WIDTH, PROJECT_HEIGHT, WIDTH/2 - PROJECT_WIDTH/2, HEIGHT/2 - PROJECT_HEIGHT/2);
        ((Component)p).setFocusable(true);

        getContentPane().add(p);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
