package src.WindowComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class Layer {
    private BufferedImage buf;
    private Graphics g;
    private int x;
    private int y;

    public Layer(int width, int height) {
        buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = buf.createGraphics();
    }
    public BufferedImage getImage() {
        return buf;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void draw(Brush b, int x, int y) {
        b.draw(g, x, y);
    }
}
