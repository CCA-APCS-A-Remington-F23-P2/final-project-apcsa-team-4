package src.UIComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ColorPicker {

    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage background;

    public ColorPicker(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        background = null ;
        try {background = ImageIO.read(new File("colorPicker.jpg")); } catch (Exception e) {}
    }

    public Color pick(int mouseX, int mouseY) {
        int[] rgb = background.getRaster().getPixel((int)((mouseX-x)*(background.getWidth()/(width*1.0))), (int)((mouseY-y)*(background.getHeight()/(height*1.0))), new int[3]);
        return new Color(rgb[0], rgb[1], rgb[2]);
    }

    public void draw(Graphics window) {
        Graphics2D g = (Graphics2D) window;
        g.setColor(new Color(0, 0, 0));
        g.drawImage(background, x, y, width, height, null);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
