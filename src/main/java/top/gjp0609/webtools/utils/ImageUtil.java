package top.gjp0609.webtools.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author guojinpeng
 * @date 17.11.9 13:44
 */
public class ImageUtil {
    public static BufferedImage zoomImage(BufferedImage sourceImage, int width, int height) {
        BufferedImage image;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(sourceImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
        return image;
    }
}
