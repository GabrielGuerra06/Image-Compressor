package edu.up.isgc.cg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/* DESCOMPRESS CLASS
 * This class makes all the descompress of the image and its called directly in the Tester class
 */
public class Decompressor {

    //the method drawImage recieves a byte indexed image type and convert it to a new image type rgb to decompress the image given
    public static BufferedImage createImageDescompressed(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixel = image.getRGB(x,y);
                Color color = new Color(pixel);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                color = new Color(red, green, blue);
                newImage.setRGB(x,y,color.getRGB());
            }
        }
        return newImage;
    }

    //the method descompress create a new file with the final image descompressed by receiving the names of the files
    static void decompress(String filenameCompressed, String filenameDescompressed){
        try {
            File finalFile =  new File(filenameCompressed);
            BufferedImage  imageDescompressed = createImageDescompressed(ImageIO.read(finalFile));
            ImageIO.write(imageDescompressed, "bmp", new File(filenameDescompressed));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
