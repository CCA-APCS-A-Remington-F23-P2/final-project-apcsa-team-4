package src.UIComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ColorPicker implements UIComponent {

    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage background;
    private int barHeight = 15;
    private Color currColor;

    public ColorPicker(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        background = null ;
        currColor = new Color(0, 0, 0);
        try {background = ImageIO.read(new File("assets/colorPicker.jpg")); } catch (Exception e) {}
    }

    public Color pick(int mouseX, int mouseY) {
        int[] rgb = background.getRaster().getPixel((int)((mouseX-x)*(background.getWidth()/(width*1.0))), (int)((mouseY-y)*(background.getHeight()/(height-barHeight*1.0))), new int[3]);
        currColor = new Color(rgb[0], rgb[1], rgb[2]);
        return currColor;
    }

    public void draw(Graphics window) {
        Graphics2D g = (Graphics2D) window;
        g.setColor(new Color(0, 0, 0));
        g.drawImage(background, x, y, width, height-barHeight, null);
        g.setColor(currColor);
        g.fillRect(x, y+height-barHeight, width, barHeight);
        g.setColor(currColor.getRed()+currColor.getBlue()+currColor.getGreen()<320 ? new Color(255, 255, 255):new Color(0, 0, 0));
        g.drawString(""+currColor.getRed() + ", " + currColor.getGreen() + ", " + currColor.getBlue(), x, y+height-5);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return currColor;
    }

    public int barHeight() {
        return barHeight;
    }
}
