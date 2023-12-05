package src.WindowComponent;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Component;

public class Window extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public static final int PROJECT_WIDTH = 800;
    public static final int PROJECT_HEIGHT = 600;


    public Window() {
        super("Paint--");
        setSize(WIDTH,HEIGHT);

        paint p = new paint(PROJECT_WIDTH, PROJECT_HEIGHT, WIDTH/2 - PROJECT_WIDTH/2, 0);
        UI u = new UI(200, 200, 0, 600);
        ((Component)p).setFocusable(false);
        ((Component)u).setFocusable(false);

        getContentPane().add(p, BorderLayout.WEST);
        getContentPane().add(u, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // getContentPane().setLayout(new FlowLayout());
        setVisible(true);
    }
}
