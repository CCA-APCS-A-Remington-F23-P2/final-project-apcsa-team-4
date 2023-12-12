package src.WindowComponent;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class Layer {
    private BufferedImage buf;
    private Graphics g;
    private int alpha;
    private int x;
    private int y;
    private int windowX;
    private int windowY;
    private boolean visible;

    public Layer(int width, int height, int windowX, int windowY) {
        buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = buf.createGraphics();
        this.windowX = windowX;
        this.windowY = windowY;
        visible = true;
    }
    public BufferedImage getImage() {
        if (!visible) {
            return new BufferedImage(buf.getWidth(), buf.getHeight(), BufferedImage.TYPE_INT_ARGB);
        }

        return buf;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public BufferedImage getImageEvenIfNotVis() {
        return buf;
    }

    public void draw(Brush b, int x, int y, boolean dl) {
       // System.out.println(visible);
        b.draw(g, x, y, dl);
    }

    public void fill(Brush b, int x, int y) {
        // Floodfill algorithm for this layer
        if (x < 0 || x >= buf.getWidth() || y < 0 || y >= buf.getHeight()) {
            return;
        }
        
        int targetColor = buf.getRGB(x, y);
        int fillColor = b.getColor().getRGB();
        
        if (targetColor == fillColor) {
            return;
        }
        
        ArrayList<int[]> queue = new ArrayList<>();
        queue.add(new int[]{x, y});
        
        while (!queue.isEmpty()) {
            int[] pixel = queue.remove(queue.size() - 1);
            int px = pixel[0];
            int py = pixel[1];
            
            if (px < 0 || px >= buf.getWidth() || py < 0 || py >= buf.getHeight()) {
                continue;
            }
            
            if (buf.getRGB(px, py) != targetColor) {
                continue;
            }
            
            buf.setRGB(px, py, fillColor);
            
            queue.add(new int[]{px - 1, py});
            queue.add(new int[]{px + 1, py});
            queue.add(new int[]{px, py - 1});
            queue.add(new int[]{px, py + 1});
        }
    }
}
