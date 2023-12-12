package src.UIComponent;
//This class allows switching between the fill and paint tools

import java.awt.Graphics;

public class ToolPicker extends UIComponent {
    private boolean fillToolSelected;
    private boolean paintToolSelected;
    private int 

    public ToolPicker() {
        fillToolSelected = false;
        paintToolSelected = true;
    }

    public void selectFillTool() {
        fillToolSelected = true;
        paintToolSelected = false;
    }

    public void selectPaintTool() {
        fillToolSelected = false;
        paintToolSelected = true;
    }

    public boolean isFillToolSelected() {
        return fillToolSelected;
    }

    public boolean isPaintToolSelected() {
        return paintToolSelected;
    }

    public void draw(Graphics window) {
        //This should draw a nice GUI for tool picker
    }
}
