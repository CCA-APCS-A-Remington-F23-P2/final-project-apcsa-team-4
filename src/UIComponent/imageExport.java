package src.UIComponent;

import java.io.*;
import javax.imageio.ImageIO;
import java.lang.Exception.*;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.util.Arrays;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import src.WindowComponent.Layer;
import src.WindowComponent.paint;
import java.util.ArrayList;
import java.util.List;
import java.nio.ByteBuffer;
import java.awt.Dimension;

public class imageExport {

    public static final byte[] sep = (new String("LSEP")).getBytes();

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
    public static void exportRaw(paint p) throws IOException {
        byte[] outBytes;
        int length = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ArrayList<Layer> lay = p.getLayers();
            for (Layer l:lay) {
                ImageIO.write(l.getImageEvenIfNotVis(), "png", bos);
                byte[] x = ByteBuffer.allocate(4).putInt(l.getX()).array();
                byte[] y = ByteBuffer.allocate(4).putInt(l.getY()).array();
                byte[] a = ByteBuffer.allocate(4).putInt(l.getAlpha()).array();
                bos.write(x, 0, 4);
                bos.write(y, 0, 4);
                bos.write(a, 0, 4);
                bos.write(sep, 0, sep.length);
            }
        }finally{
        }
        try (FileOutputStream fos = new FileOutputStream("test.pmm")) {
            outBytes = bos.toByteArray();
            System.out.println(outBytes.length);
            fos.write(outBytes);
            fos.close();
            bos.close();
        }
    }
    public static void loadRaw(paint p) throws IOException, ClassNotFoundException {
         byte[] fileContent = Files.readAllBytes(Paths.get("test.pmm"));
         System.out.println(fileContent.length);
         ArrayList<Layer> l = new ArrayList<Layer>();
         ByteArrayOutputStream cLayer = new ByteArrayOutputStream();
         ByteArrayOutputStream sCheck = new ByteArrayOutputStream();
         Dimension size = p.getPreferredSize();
         for (byte b:fileContent) {
             Layer ln = new Layer((int)size.getWidth(), (int)size.getHeight(), 0, 0);
             cLayer.write(b);
             if (b == sep[sCheck.toByteArray().length]) {
                 sCheck.write(b);
                 byte[] sArr = sCheck.toByteArray();
                 if (sArr.length == sep.length) {
                     byte[] imgBuf = Arrays.copyOfRange(cLayer.toByteArray(), 0, cLayer.toByteArray().length-sep.length-12);
                     ByteArrayInputStream imgIn = new ByteArrayInputStream(imgBuf);
                     try {
                         ln.setBuf(ImageIO.read(imgIn));
                         l.add(ln);
                         cLayer.reset();
                     } catch (IOException e) {
                         throw new RuntimeException(e);
                     }
                     sCheck.reset();
                 }
             } else {
                 sCheck.reset();
             }
         }

         for (Layer lay:l) p.addLayer(lay);
    }
}
/*
  ByteArrayOutputStream baos = new ByteArrayOutputStream();
  ImageIO.write(img, "jpg", baos);
  byte[] bytes = baos.toByteArray();
 */
