package src.WindowComponent;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.image.RescaleOp;
import javax.imageio.ImageIO;

public class Transform {

    public static BufferedImage zoom(BufferedImage buf, int x, int y, double percent) {
        int width = buf.getWidth();
        int height = buf.getHeight();
        // Calculate the new width and height based on the zoom percentage
        int newWidth = (int) (width * percent);
        int newHeight = (int) (height * percent);
        // Calculate the new coordinates for the zoomed image
        int newX = x - (newWidth / 2);
        int newY = y - (newHeight / 2);
        // Create a new BufferedImage for the zoomed image
        BufferedImage zoomedImage = new BufferedImage(newWidth, newHeight, buf.getType());
        Graphics g = zoomedImage.getGraphics();
        // Draw the zoomed image
        g.drawImage(buf, 0, 0, newWidth, newHeight, newX, newY, newX + newWidth, newY + newHeight, null);
        g.dispose();
        return zoomedImage;
    }

    //crops the image according to the given bounds in the parameters
    public static BufferedImage crop(BufferedImage buf, int xMin, int xMax, int yMin, int yMax) {
        int width = xMax - xMin;
        int height = yMax - yMin;
        BufferedImage croppedImage = new BufferedImage(width, height, buf.getType());
        Graphics g = croppedImage.getGraphics();
        g.drawImage(buf, 0, 0, width, height, xMin, yMin, xMax, yMax, null);
        g.dispose();
        return croppedImage;
    }
}