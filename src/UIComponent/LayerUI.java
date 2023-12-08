package src.UIComponent;
import src.WindowComponent.Layer;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JSlider;


public class LayerUI extends Button implements UIComponent{
    private Layer layer;
    private boolean displayed;
   private Slider alphaSlider;


    public LayerUI(Layer layer, int x, int y, int width, int height) {
        super(x,y,width,height);
        this.layer = layer;
        this.displayed = true;
        alphaSlider = new Slider(x+layer.getImage().getWidth()/10, y, 0, 255, 255);
    }



    @Override
    public void draw(Graphics window) {
        int layerWidth = layer.getImage().getWidth();
        int layerHeight = layer.getImage().getHeight();
        window.drawImage(layer.getImage().getScaledInstance(this.getHeight(), this.getHeight(), 0), this.getX(), this.getY(), null);
        alphaSlider.draw(window);
    }
}
