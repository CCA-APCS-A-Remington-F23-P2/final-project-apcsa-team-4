package src.WindowComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class paint extends Canvas implements KeyListener, MouseListener, Runnable, MouseMotionListener {

    private Color col;
    private ArrayList<Layer> layers;
    private Layer curr;
    private Brush b;
    private int width;
    private int height;
    private int x;
    private int y;
    private boolean mouseDown = false;
    private int mouseX = 0;
    private int mouseY = 0;


    public paint(int cWidth, int cHeight, int x, int y) {
        col = new Color(0, 0, 0);
        layers = new ArrayList<Layer>();
        b = new Brush(new Rectangle(0, 0, 20, 20));
        curr = new Layer(cWidth, cHeight, x, y);
        layers.add(curr);
        this.x = x;
        this.y = y;

        width = cWidth;
        height = cHeight;

        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
        new Thread(this).start();
    }

    public void update(Graphics window)
    {
        paint(window);
    }
    public void paint(Graphics window) {

        window.setColor(new Color(255, 255, 255));
        window.drawRect(x, y, width, height);
        for (Layer l:layers) {
            window.drawImage(l.getImage(),l.getX()+x,l.getY()+y, null);
        }
        if (mouseDown) {
            curr.draw(b, mouseX, mouseY);
        }
    }
    public void keyPressed(KeyEvent e) {

    }
    public void keyReleased(KeyEvent e) {

    }
    public void keyTyped(KeyEvent e) {

    }
    public void run()
    {
        try {
            while(true) {
                Thread.currentThread().sleep(16);
                repaint();
            }
        }catch(Exception e) {
            System.out.println("Screw you there was an error but we're too lazy to tell you what happened");
        }
  }
    public void mouseClicked(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
    }

    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
    }
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX()-x;
        mouseY = e.getY()-y;
    }
    public void mouseDragged(MouseEvent e) {
        mouseDown = true;

        mouseX = e.getX()-x;
        mouseY = e.getY()-y;
    }
}
