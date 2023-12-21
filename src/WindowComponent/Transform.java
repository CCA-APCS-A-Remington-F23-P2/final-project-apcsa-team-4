package src.WindowComponent;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.image.RescaleOp;
import javax.imageio.ImageIO;

public class Transform extends fluid{

    //crops the image according to the given bounds in the parameters
    public static BufferedImage crop(BufferedImage buf, int xMin, int xMax, int yMin, int yMax) {
        int width = xMax - xMin;
        int height = yMax - yMin;
        BufferedImage croppedImage = buf.getSubimage(0, 0, width, height);
        return croppedImage;
    }
}