package src.UIComponent;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Button implements UIComponent{
    private boolean pressed = false;
    private boolean displayed;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;
    private ImageIcon image;
    private static boolean prevMousePressed = false;
    private double prevPressTime = System.currentTimeMillis();


    public Button(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.displayed = true;
        color = Color.BLACK;
    }

    public Button(int x, int y, int width, int height, Color color) {
        this(x,y,width,height);
        this.color = color;
    }

    public Button(int x, int y, int width, int height, ImageIcon image) {
        this(x,y,width,height);
        this.image = image;
    }


    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void draw(Graphics window) {
        if (!displayed) {
            return;
        }

        if (image!=null) {
            window.drawImage(image.getImage(), x, y, width, height, null);
            return;
        }

        window.setColor(color);
        window.drawRect(x, y, width, height);
    }


    public void setDisplayed(boolean displayed) {
        this.displayed = displayed;
    }

    public void setIcon(ImageIcon image) {
        this.image = image;
    }

    public void setColor(Color color) {
        this.color = color;
    }


    public void setY(int y) {
        this.y = y;
    }

    public boolean isClicked(int mouseX, int mouseY, boolean mousePressed) {
        if (mouseX>=x&&mouseX<=x+width&&mouseY>=y&&mouseY<=y+height) {
            if (mousePressed&&!prevMousePressed) {
                pressed = true;
                prevPressTime = System.currentTimeMillis();
            } else {
                pressed = false;
            }
        }
        prevMousePressed = mousePressed;
        return pressed; 
    }
}
