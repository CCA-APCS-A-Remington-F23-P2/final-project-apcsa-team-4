package src.UIComponent;
//This class allows switching between the fill and paint tools

import java.awt.Graphics;

public class ToolPicker implements UIComponent {
    private boolean fillToolSelected;
    private boolean paintToolSelected;
    private int x;
    private int y;
    private int width;
    private int height;

    public ToolPicker() {
        fillToolSelected = false;
        paintToolSelected = true;
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

    public void selectFillTool() {
        fillToolSelected = true;
        paintToolSelected = false;
    }

    public void selectpaintTool() {
        fillToolSelected = false;
        paintToolSelected = true;
    }

    public boolean isFillToolSelected() {
        return fillToolSelected;
    }

    public boolean ispaintToolSelected() {
        return paintToolSelected;
    }

    public void draw(Graphics window) {
        //This should draw a nice GUI for tool picker
        
    }
}
