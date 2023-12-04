import javax.swing.JFrame;
import java.awt.Component;

public class Window extends JFrame {

    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;


    public Window() {
        super("Paint--");
        setSize(WIDTH,HEIGHT);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
