package edu.up.isgc.cg;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;

/* ImageProperties class
 * defineRGB copies the colors into a specified array to get the new colors of my image compressed, recieves an index color model and byte array for each color
 * The drawImage method recieves a pixel array and with 2 cycles sets the colors */



public class ImageProperties {
    // defineRGB its called in "changeColorRange" inside compressor class
    static void defineRGB(IndexColorModel icm, byte[] r,byte[] g, byte[] b ){
        icm.getReds(r);
        icm.getGreens(g);
        icm.getBlues(b);
    }

    //drawImage its called in  "createImage" inside compressor class
    static void drawImage(Color[][] pixelMatrix) {
        BufferedImage img = new BufferedImage(pixelMatrix.length, pixelMatrix[0].length, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < pixelMatrix.length; x++) {
            for (int y = 0; y < pixelMatrix[x].length; y++) {
                img.setRGB(x, y, pixelMatrix[x][y].getRGB());
            }
        }
    }






}
