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

import src.UIComponent.ColorPicker;

import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

public class UI extends Canvas implements MouseListener, Runnable, MouseMotionListener {

    private ColorPicker cp;
    private paint p;
    private int width;
    private int height;
    private int x;
    private int y;
    private boolean mouseDown = false;
    private int mouseX = 0;
    private int mouseY = 0;
    private boolean[] keys = new boolean[2];
    private boolean[]keysPrev = new boolean[2];

    public UI(int cWidth, int cHeight, int x, int y, paint p) {
        this.x = x;
        this.y = y;
        this.p = p;
        cp = new ColorPicker(x, y, cWidth-1, cWidth-1);

        width = cWidth;
        height = cHeight;

        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        new Thread(this).start();
    }

    public void update(Graphics window)
    {
        paint(window);
    }

    public void paint(Graphics window) {
        BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        
        g.setStroke(new BasicStroke(3));
        g.setColor(new Color(170, 170, 170));
        g.drawRect(x-2, y-1, width+2, height+2);
        if (mouseDown&&mouseX>=cp.getX()&&mouseX<=cp.getX()+cp.getWidth()&&mouseY>=cp.getY()&&mouseY<=cp.getY()+cp.getHeight()-cp.barHeight()) {
            p.recolor(cp.pick(mouseX, mouseY));
        } 
        if (mouseX>=cp.getX()+cp.getWidth()-cp.barHeight()*3&&mouseX<=cp.getX()+cp.getWidth()-cp.barHeight()*2&&mouseY>=cp.getY()+cp.getHeight()-cp.barHeight()&&mouseY<=cp.getY()+cp.getHeight()) {
            if (keys[0]) cp.changeRed(1);
            if (keys[1]) cp.changeRed(-1);
        }
        if (mouseX>=cp.getX()+cp.getWidth()-cp.barHeight()*2&&mouseX<=cp.getX()+cp.getWidth()-cp.barHeight()&&mouseY>=cp.getY()+cp.getHeight()-cp.barHeight()&&mouseY<=cp.getY()+cp.getHeight()) {
            if (keys[0]) cp.changeGreen(1);
            if (keys[1]) cp.changeGreen(-1);
        }
        if (mouseX>=cp.getX()+cp.getWidth()-cp.barHeight()&&mouseX<=cp.getX()+cp.getWidth()&&mouseY>=cp.getY()+cp.getHeight()-cp.barHeight()&&mouseY<=cp.getY()+cp.getHeight()) {
            if (keys[0]) cp.changeBlue(1);
            if (keys[1]) cp.changeBlue(-1);
        }
        p.recolor(cp.getColor());
        cp.draw(window);
        
        Graphics2D g2dComponent = (Graphics2D) window;
        g2dComponent.drawImage(bufferedImage, null, 0, 0); 
    }
    public void keyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[1] = true;
        }
    }
    public void keyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[1] = false;
        }
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

    public Dimension getPreferredSize() {return new Dimension(width, height);}

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
