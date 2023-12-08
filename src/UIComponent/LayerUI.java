package src.UIComponent;
import src.WindowComponent.Layer;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JSlider;


public class LayerUI extends Button implements UIComponent{
    private Layer layer;
    private boolean displayed;
    private JSlider alphaSlider;
    private Button moveUpButton;
    private Button hideButton;
    private Button moveDownButton;
    private Button deleteButton;


    public LayerUI(Layer layer, int x, int y, int width, int height) {
        super(x,y,width,height);
        this.layer = layer;
        this.displayed = true;
        int buttonWidth = width/10;
        moveUpButton = new Button(x+width/2+10, y+10, buttonWidth, buttonWidth);
        hideButton = new Button(x+width/2+10, moveUpButton.getY()+buttonWidth + 10, buttonWidth, buttonWidth);
        moveDownButton = new Button(x+width/2+10, hideButton.getY()+buttonWidth + 10, buttonWidth, buttonWidth);
        deleteButton = new Button(x+width-10, y, buttonWidth/2, buttonWidth/2);
        //alphaSlider = new Slider(x+layer.getImage().getWidth()/10, y, 0, 255, 255);
        alphaSlider = new JSlider( JSlider.HORIZONTAL, 0, 255, 255 );
    }


    //write getters for buttons

    public Button getMoveUpButton() {
        return moveUpButton;
    }

    public Button getHideButton() {
        return hideButton;
    }

    public Button getMoveDownButton() {
        return moveDownButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Layer getLayer() {
        return layer;
    }


    public void setY(int y) {
        super.setY(y);
        moveUpButton.setY(y+10);
        hideButton.setY(moveUpButton.getY()+moveUpButton.getHeight() + 10);
        moveDownButton.setY(hideButton.getY()+hideButton.getHeight() + 10);
        deleteButton.setY(y);
    }



    @Override
    public void draw(Graphics window) {
        int layerWidth = layer.getImage().getWidth();
        int layerHeight = layer.getImage().getHeight();
        window.drawImage(layer.getImage().getScaledInstance(this.getHeight(), this.getHeight(), 0), this.getX(), this.getY(), null);
        moveUpButton.draw(window);
        hideButton.draw(window);
        moveDownButton.draw(window);
        deleteButton.draw(window);
    }
}
