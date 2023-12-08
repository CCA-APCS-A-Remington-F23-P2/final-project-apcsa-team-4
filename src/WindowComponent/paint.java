package src.WindowComponent;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
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

public class paint extends Canvas implements MouseListener, Runnable, MouseMotionListener {

    private Color col;
    private UI ui;
    private ArrayList<Layer> layers;
    private Layer curr;
    private Brush b;
    private int width;
    private int height;
    private int x;
    private int y;
    private boolean mouseDown = false;
    private boolean inDraw = false;
    private int mouseX = 0;
    private int mouseY = 0;
    private long systime = 0;
    private static final long LINEDELAY = 30;
    private long clickTime = 0;
    private boolean shiftDraw = false;
    private boolean fill = false;
    private Brush eraser;
    private boolean erase = false;

    public paint(int cWidth, int cHeight, int x, int y) {

        setVisible(true);
        setFocusable(true);

        col = new Color(0, 0, 0);
        layers = new ArrayList<Layer>();
        b = new Brush(new Rectangle(0, 0, 20, 20));
        eraser = new Brush(new Rectangle(0, 0, 20, 20));
        eraser.recolor(new Color(255, 255, 255));
        curr = new Layer(cWidth, cHeight, x, y);
        layers.add(curr);
        this.x = x;
        this.y = y;

        width = cWidth;
        height = cHeight;

        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        new Thread(this).start();
    }


    public void setUI(UI ui) {
        this.ui = ui;
    }

    public void update(Graphics window)
    {
        systime = System.currentTimeMillis();
        paint(window);
    }
    
    public void paint(Graphics window) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.setStroke(new BasicStroke(3));
        g.clearRect(0, 0, width, height);
        g.setColor(new Color(200, 200, 200));
        g.fillRect(0, 0, width, height);
        g.setColor(new Color(170, 170, 170));
        g.drawRect(0, 0, width, height);

        for (Layer l:layers) {
            g.drawImage(l.getImage(),0,0, null);
        }

        if (!erase) {
            if (mouseDown && !fill) {
                curr.draw(b, mouseX, mouseY, inDraw || shiftDraw);
            } else if (mouseDown && fill) {
                curr.fill(b, mouseX, mouseY);
            }
        } else {
            if (mouseDown && !fill) {
                curr.draw(eraser, mouseX, mouseY, inDraw || shiftDraw);
            } else if (mouseDown && fill) {
                curr.fill(eraser, mouseX, mouseY);
            }
        }
        Graphics2D g2dComponent = (Graphics2D) window;
        g2dComponent.drawImage(bufferedImage, null, x, y); 
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
        clickTime = systime;
    }

    public Dimension getPreferredSize() {return new Dimension(width, height);}

    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
        inDraw = false;
    }
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX()-x;
        mouseY = e.getY()-y;
    }
    public void mouseDragged(MouseEvent e) {
        mouseDown = true;
        if (systime > clickTime + LINEDELAY) inDraw = true;

        mouseX = e.getX()-x;
        mouseY = e.getY()-y;
    }

    public void recolor(Color c) {
        b.recolor(c);
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Layer getCurr() {
        return curr;
    }
    public void setCurr(int index) {
        curr = layers.get(index);
    }

    //create a new layer and then return it
    public Layer addLayer() {
        layers.add(new Layer(width, height, x, y));
        return layers.get(layers.size()-1);
    }
    public void keyPress(KeyEvent e) {
       if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
           shiftDraw = true;
       }
       if (e.getKeyCode() == KeyEvent.VK_F) {
           fill = true;
       }
       if (e.getKeyCode() == KeyEvent.VK_E) {
           erase = true;
       }
    }
    public void keyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shiftDraw = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_F) {
           fill = false;
       }
       if (e.getKeyCode() == KeyEvent.VK_E) {
           erase = false;
       }
    }

    public void removeLayer(int index) {
        layers.remove(index);
    }

    //hide layer
    public void hideLayer(Layer l) {
        l.setVisible(false);
    }

    //move layer up

    //move layer down
}
