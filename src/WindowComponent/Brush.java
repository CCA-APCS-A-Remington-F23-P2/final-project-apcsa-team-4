package src.WindowComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.File;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Rectangle;
import java.awt.image.RescaleOp;

public class Brush {

    private Shape s;//for simple brushes
    private String filePath;
    private BufferedImage img;//for complex brushes
    private BufferedImage wImg;
    private double size;
    private double scaleFactor = 1;
    private int width;
    private int height;
    private Color color;
    private int lastX;
    private int lastY;

    public Brush() {}
    public Brush(Shape s) {
        img = new BufferedImage((int)s.getBounds().getWidth(), (int)s.getBounds().getHeight(), BufferedImage.TYPE_INT_ARGB);
        wImg = new BufferedImage((int)s.getBounds().getWidth(), (int)s.getBounds().getHeight(), BufferedImage.TYPE_INT_ARGB);
        color = new Color(0, 0, 0);
        Color white = new Color(255, 255, 255);
        this.s = s;
        this.width = (int)s.getBounds().getWidth();
        this.height = (int)s.getBounds().getHeight();
        size = (this.width+this.height)/2;
        for (int x=0;x<s.getBounds().getWidth();x++) {
            for (int y=0;y<s.getBounds().getHeight();y++) {
                if (s.contains(x,y)) {img.setRGB(x, y, color.getRGB());wImg.setRGB(x, y, white.getRGB());}
            }
        }
    }
    public Brush(String fP) {
        color = new Color(0, 0, 0);
        this.filePath = fP;
        try
        {
            URL url = getClass().getResource(fP);
            Image image= ImageIO.read(url);
            img = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            this.width = image.getWidth(null);
            this.height = image.getHeight(null);
            wImg = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

            // Draw the image on to the buffered image
            Graphics2D bGr = img.createGraphics();
            Graphics2D bwGr = wImg.createGraphics();
            bGr.drawImage(image, 0, 0, null);
            bwGr.drawImage((new RescaleOp(255, 1, null)).filter((BufferedImage)image, null), 0, 0, null);
            bGr.dispose();
            bwGr.dispose();

        }
        catch(Exception e)
        {
            System.out.println("Couldn't Load Brush Texture");
        }
    }
    public void resize(int inc) {
        if (size+inc>1000) return;
        if (size+inc<1) return;

        scaleFactor = (size+inc)/size;
        size += inc;
        width = (int)(width*scaleFactor);
        height = (int)(height*scaleFactor);
        System.out.println(size);
        Image scaledImg = ((Image)img).getScaledInstance((int)(width*scaleFactor), (int)(height*scaleFactor), Image.SCALE_DEFAULT);
        Image scaledWImg = ((Image)wImg).getScaledInstance((int)(width*scaleFactor), (int)(height*scaleFactor), Image.SCALE_DEFAULT);
        img = new BufferedImage((int)(width*scaleFactor), (int)(height*scaleFactor), BufferedImage.TYPE_INT_ARGB);
        wImg = new BufferedImage((int)(width*scaleFactor), (int)(height*scaleFactor), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = img.createGraphics();
        Graphics2D bwGr = wImg.createGraphics();
        bGr.drawImage(scaledImg, 0, 0, null);
        bwGr.drawImage(scaledWImg, 0, 0, null);
        bGr.dispose();
        bwGr.dispose();
    }
    private void setColor(float[] scales) {
        Graphics2D bGr = img.createGraphics();
        RescaleOp rop = new RescaleOp(scales, new float[4], null);
        bGr.drawImage(rop.filter(wImg, null), 0, 0, null);
        bGr.dispose();
        color = new Color(scales[0], scales[1], scales[2], scales[3]);

    }
    public void recolor(Color c) {
        setColor(c.getComponents(new float[4]));
    }
    public void draw(Graphics window, int x, int y, boolean doLine) {
        window.drawImage(img, x, y, null);
    }

    public Color getColor() {
        return color;
    }

    public double getSize() {
        return size;
    }
}
