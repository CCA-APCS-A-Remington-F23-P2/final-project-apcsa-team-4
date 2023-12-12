package src.UIComponent;

import java.io.*;
import javax.imageio.ImageIO;
import java.lang.Exception.*;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

public class imageExport {
    public static void export(String name, BufferedImage out) {
        try {
            if (name == null) {
                name = JOptionPane.showInputDialog("File name please: ");
            }
            File outputfile = new File(name+".png");
            ImageIO.write(out, "png", outputfile);
        } catch (IOException e) {
            System.out.println("There was a problem exporting.");
        }
    }
}
