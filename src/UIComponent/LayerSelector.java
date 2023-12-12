package src.UIComponent;

import src.WindowComponent.Layer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import javax.imageio.ImageIO;


public class LayerSelector implements UIComponent {
    private final ArrayList<LayerUI> layers;
    private int x;
    private int y;
    private final int width;
    private final int height;
    private int selectedLayer; 
    private final Button newLayerButton;

    public LayerSelector(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        layers = new ArrayList<LayerUI>();
        newLayerButton = new Button(x, y, width/10, width/10, new ImageIcon("assets/icons/plus.png"));
        this.width = width;
        this.height = height;
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

    public int getSelectedLayer() {
        return selectedLayer;
    }

    public ArrayList<LayerUI> getLayers() {
        return layers;
    }

    public void addLayer(Layer layer) {
            layers.add(new LayerUI(layer, x, y + newLayerButton.getHeight() + 1 + ((height/12)+2) * layers.size(), width, height/12));
    }

    public Button getNewLayerButton() {
        return newLayerButton;
    }

    @Override
    public void draw(Graphics window) {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bufferedImage.createGraphics();
        
        window.setColor(new Color(170, 170, 170));
        window.fillRect(x, y, width, height);
        newLayerButton.draw(window);

        for (int i = 0; i < layers.size(); i++) {
            LayerUI layer = layers.get(i);




            layer.draw(g);

            window.setColor(new Color(230, 230, 230));
            window.fillRect(layer.getX()+1, layer.getY()+1, layer.getWidth()-2, layer.getHeight()-3);

            if (layers.indexOf(layer)==selectedLayer) {
                window.setColor(new Color(0, 0, 0));
                window.drawRect(layer.getX()+1, layer.getY()+1, layer.getWidth()-2, layer.getHeight()-3);
            } else {
                window.setColor(new Color(170, 170, 170));
                window.drawRect(layer.getX()-2, layer.getY()-1, layer.getWidth()+2, layer.getHeight()+2);
            }
        }

        Graphics2D g2dComponent = (Graphics2D) window;
        
        g2dComponent.drawImage(bufferedImage, null, 0, 0); 
    }

    public void setSelectedLayer(int indexOf) {
        selectedLayer = indexOf;
    }


    //switch 2 layers by their indexs method

    public void switchLayers(int indexOf1, int indexOf2) {
        int tempY = layers.get(indexOf1).getY();
        int tempY2 = layers.get(indexOf2).getY();

        LayerUI temp = layers.get(indexOf1);
        //change the x and y of each layer to the other layer



        layers.set(indexOf1, layers.get(indexOf2));
        layers.set(indexOf2, temp);
        layers.get(indexOf1).setY(tempY);
        layers.get(indexOf2).setY(tempY2);
    }


    public void removeLayer(int indexOf) {
        layers.remove(indexOf);
        for (int i = indexOf ; i < layers.size(); i++) {
            layers.get(i).setY(layers.get(i).getY()-height/12-2);
        }
        if (layers.size()==1) setSelectedLayer(0);
    }
}
