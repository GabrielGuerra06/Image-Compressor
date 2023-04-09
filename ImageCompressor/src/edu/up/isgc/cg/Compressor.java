package edu.up.isgc.cg;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.*;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import static edu.up.isgc.cg.ImageProperties.defineRGB;
import static edu.up.isgc.cg.ImageProperties.drawImage;

/* COMPRESS CLASS
 * This class makes all the process to compress the image and its called directly in the Tester class
 */
public class Compressor {



//changeColorRange starts the compression, receiving my image and the dimensions, this method defines a new IndexColoModel
//calculating the size of the image by making a map  and calculating the new color of my image with the help of the function defineRGB and a raster
    public static BufferedImage changeColorRange(BufferedImage image, int x, int y) {
        ColorModel cm = image.getColorModel();
        IndexColorModel icm = (IndexColorModel) cm;
        WritableRaster raster = image.getRaster();
        int pixel = raster.getSample(x, y,0);
        int size = icm.getMapSize();
        byte[] reds = new byte[size];
        byte[] greens = new byte[size];
        byte[] blues = new byte[size];
        defineRGB(icm,reds,greens, blues);
        IndexColorModel icm2 = new IndexColorModel(8, size, reds, greens, blues, pixel);
        return new BufferedImage(icm2, raster, false, null);
    }

    //the method createImage makes a new byte indexed image and draw it with the new image properties recieved
    public static BufferedImage createImageCompressed(BufferedImage image) {
        BufferedImage compressed = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_INDEXED);
        compressed = changeColorRange(compressed, 0, 0);
        compressed.createGraphics().drawImage(image, 0, 0, null);
        return compressed;
    }

//the method compress creates the new file by receiving the compressed image
     static void compress(Color[][] pixelMatrix, String filename) throws IOException {
         drawImage(pixelMatrix);
         String replaceFileName=filename.replace(".compressed","");
         File file =  new File(replaceFileName + ".bmp");
        BufferedImage  image = createImageCompressed(ImageIO.read(file));
        ImageIO.write(image, "bmp", new File( replaceFileName + ".compressed"));

    }


}

