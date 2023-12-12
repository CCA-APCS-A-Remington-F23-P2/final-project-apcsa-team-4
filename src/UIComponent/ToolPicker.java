package src.UIComponent;
//This class allows switching between the fill and paint tools


public class ToolPicker {
    private boolean fillToolSelected;
    private boolean paintToolSelected;

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
}
