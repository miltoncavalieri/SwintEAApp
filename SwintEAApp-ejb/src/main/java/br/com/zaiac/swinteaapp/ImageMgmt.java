package br.com.zaiac.swinteaapp;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import static javax.xml.bind.DatatypeConverter.parseBase64Binary;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

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
    
    public static String encodeImageToBase64 (String fileName, InputStream is) {
        BufferedImage image = null;
        String encodedfile = "";
        byte[] imageByte;
        try {
            String ext = FilenameUtils.getExtension(fileName);
            BufferedImage baos = resizeImage(is, ext, 640, 480);
            
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            ImageIO.write(baos, ext, outStream);             
            
            InputStream is2 = new ByteArrayInputStream(outStream.toByteArray());
            byte[] bytes = IOUtils.toByteArray(is2);
            
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
            return encodedfile;
        } catch (IOException e) {
            return null;
        }
    }

    public static InputStream encodeBase64ToImage (String is) {
        BufferedImage image = null;
        byte[] imageByte;
//        String ext = FilenameUtils.getExtension(fileName);
//        ext = ext.toLowerCase();
        String base64Image = is;
        byte[] imageBytes = parseBase64Binary(base64Image);  
        return new ByteArrayInputStream(imageBytes);
    }
    
    
}
