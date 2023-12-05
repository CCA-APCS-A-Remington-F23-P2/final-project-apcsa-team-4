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

public class Brush {

    private Shape s;//for simple brushes
    private String filePath;
    private BufferedImage img;//for complex brushes
    private double size;
    private double scaleFactor = 1;
    private int width;
    private int height;
    private Color color;

    public Brush() {}
    public Brush(Shape s) {
        img = new BufferedImage((int)s.getBounds().getWidth(), (int)s.getBounds().getHeight(), BufferedImage.TYPE_INT_ARGB);
        color = new Color(0, 0, 0);
        this.s = s;
        this.width = (int)s.getBounds().getWidth();
        this.height = (int)s.getBounds().getHeight();
        for (int x=0;x<s.getBounds().getWidth();x++) {
            for (int y=0;y<s.getBounds().getHeight();y++) {
                if (s.contains(x,y)) img.setRGB(x, y, color.getRGB());
            }
        }
    }
    public Brush(String fP) {
        color = new Color(0, 0, 0);
        this.filePath = fP;
        try
        {
            URL url = getClass().getResource("fP.jpg");
            Image image= ImageIO.read(url);
            img = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

            // Draw the image on to the buffered image
            Graphics2D bGr = img.createGraphics();
            bGr.drawImage(image, 0, 0, null);
            bGr.dispose();

        }
        catch(Exception e)
        {
            System.out.println("Couldn't Load Brush Texture");
        }
    }
    public void resize() {

    }
    public void recolor() {

    }
    public void draw(Graphics window, int x, int y) {
        window.drawImage(img, x, y, null);
    }
}
