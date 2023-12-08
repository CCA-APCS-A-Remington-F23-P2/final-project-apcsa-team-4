package src.UIComponent;

import java.awt.Color;
import java.awt.Graphics;

public class Button implements UIComponent{
    private boolean pressed = false;
    private boolean displayed;
    private int x;
    private int y;
    private int width;
    private int height;


    public Button(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.displayed = true;
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
        window.setColor(Color.black);
        window.drawRect(x, y, width, height);
    }


    public boolean isClicked(int mouseX, int mouseY, boolean mousePressed) {
        if (!mousePressed) {
            pressed = false;
            return false;
        }

        if (!(mouseX>=x&&mouseX<=x+width&&mouseY>=y&&mouseY<=y+height)) {
            return false;
        }

        if (pressed) {
            return false;
        } else {
            pressed = true;
        }
        return pressed;
    }
}
