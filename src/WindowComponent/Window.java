package src.WindowComponent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Window extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    public static final int PROJECT_WIDTH = 500;
    public static final int PROJECT_HEIGHT = 400;

    public static final int UI_WIDTH = 200;
    public static final int UI_HEIGHT = 200;


    public Window() {
        super("Paint--");
        java.awt.image.BufferedImage in = null;
        File img = new File("cursor.png");
        try {
             in = ImageIO.read(img);
        } catch (IOException ex) {
            System.err.print("LMAO\n");
            ex.printStackTrace();
            System.exit(1);
        }
        setSize(WIDTH,HEIGHT);

        paint p = new paint(PROJECT_WIDTH, PROJECT_HEIGHT, WIDTH/2 - PROJECT_WIDTH/2-UI_WIDTH, 0);
        UI u = new UI(UI_WIDTH, UI_HEIGHT, 0, HEIGHT-UI_HEIGHT);
        ((Component)p).setFocusable(false);
        ((Component)u).setFocusable(false);

        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(u, BorderLayout.WEST);

        p.setCursor(getToolkit().createCustomCursor(
            new ImageIcon("cursor.png").getImage(),
            new Point(0,0),
            "My cursor"
        ));

        u.setCursor(getToolkit().createCustomCursor(
            new ImageIcon("mouse.png").getImage(),
            new Point(0,0),
            "My cursor"
        ));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // getContentPane().setLayout(new FlowLayout());
        setVisible(true);
    }
}
