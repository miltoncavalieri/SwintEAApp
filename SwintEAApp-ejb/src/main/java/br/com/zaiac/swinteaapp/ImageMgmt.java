package br.com.zaiac.swinteaapp;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;

public class ImageMgmt {
    public static ByteArrayInputStream resizeImage (File file, int width, int height) {
        try {
            BufferedImage inputImage = ImageIO.read(file);
            BufferedImage outputImage;
            if ((inputImage.getHeight() <= height) && (inputImage.getWidth() <= width)) {
                outputImage = inputImage;
            } else {
                outputImage = new BufferedImage(width, height, inputImage.getType());   
                Graphics2D g2d = outputImage.createGraphics();
                g2d.drawImage(inputImage, 0, 0, width, height, null);
                g2d.dispose();                
            }
            String ext = FilenameUtils.getExtension(file.getName());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(outputImage, ext, baos );
            baos.flush();
            return new ByteArrayInputStream(baos.toByteArray());    
        } catch (IOException e) {
            return null;
            
        }
    }
    
    public static BufferedImage resizeImage (InputStream is, String ext, int width, int height) {
        try {
            BufferedImage inputImage = ImageIO.read(is);
            BufferedImage outputImage;
            if ((inputImage.getHeight() <= height) && (inputImage.getWidth() <= width)) {
                outputImage = inputImage;
            } else {
                outputImage = new BufferedImage(width, height, inputImage.getType());   
                Graphics2D g2d = outputImage.createGraphics();
                g2d.drawImage(inputImage, 0, 0, width, height, null);
                g2d.dispose();                
            }
            return outputImage;
        } catch (IOException e) {
            return null;
        }
    }
    
}
