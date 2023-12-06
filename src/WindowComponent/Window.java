package src.WindowComponent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
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
    public static final int UI_HEIGHT = HEIGHT;


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
        

        paint p = new paint(PROJECT_WIDTH, PROJECT_HEIGHT, (WIDTH-UI_WIDTH)/2 - PROJECT_WIDTH/2, HEIGHT/2-PROJECT_HEIGHT/2-25);
        UI u = new UI(UI_WIDTH, UI_HEIGHT, 0, 0, p);
        ((Component)p).setFocusable(false);
        ((Component)u).setFocusable(false);

        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(u, BorderLayout.WEST);
        Image brush = new ImageIcon("brush.png").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT);

        p.setCursor(getToolkit().createCustomCursor(
            brush,
            new Point(0,31),
            "brush"
        ));

        u.setCursor(getToolkit().createCustomCursor(
            new ImageIcon("cursor.png").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT),
            new Point(0,0),
            "cursor"
        ));

        p.setBackground(new java.awt.Color(200,200,200));
        u.setBackground(new java.awt.Color(210,210,210));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // getContentPane().setLayout(new FlowLayout());
        setVisible(true);
    }
}
