package pictures;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.stream.ImageInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: mortenandersen
 * Date: 2006-08-23
 * Time: 12:16:03
 * To change this template use File | Settings | File Templates.
 */
public class PictureUtil {
    
    

    /**
     * From: http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4705399
     * @param source
     * @return
     * @throws IOException
     */
    public static BufferedImage readImage(Object source) throws IOException {
        ImageInputStream stream =  ImageIO.createImageInputStream(source);
        ImageReader reader = (ImageReader) ImageIO.getImageReaders(stream).next();
        reader.setInput(stream);
        ImageReadParam param = reader.getDefaultReadParam();

        ImageTypeSpecifier typeToUse = null;
        for (Iterator i = reader.getImageTypes(0); i.hasNext();) {
            ImageTypeSpecifier type = (ImageTypeSpecifier) i.next();
            if (type.getColorModel().getColorSpace().isCS_sRGB()){
                typeToUse = type;
            }
        }
        if (typeToUse != null){
            param.setDestinationType(typeToUse);
        }

        BufferedImage b = reader.read(0, param);
        reader.dispose();
        stream.close();
        return b;
    }


    public static BufferedImage toBufferedImage(java.awt.Image image, int type) {
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        BufferedImage result = new BufferedImage(w, h, type);
        Graphics2D g = result.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return result;
    }

    public static String getNameOfFile(File file){
        return file.getName().split("\\.")[0];

    }

    public static String getTypeOfFile(File file){
        return file.getName().split("\\.")[1];
    }

    public static File getFirstAvailableFile(File outputFile) {
        String[] split =  outputFile.getName().split("\\.");
        String name = split[0];
        String type = split[1];
        int i = 1;
        while ( outputFile.exists() ){
            outputFile = new File(outputFile.getParentFile(), name+"_"+i+"."+type);
            i++;
        }
        return outputFile;
    }

    public static BufferedImage scaleImageByHeight(BufferedImage bi, int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

