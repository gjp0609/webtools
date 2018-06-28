package top.gjp0609.webtools.utils;//package me.rainbow.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 二维码生成、识别工具
 * 可通过修改参数配置生成大小及格式
 *
 * @author guojinpeng
 * @date 17.11.9 10:54
 */
public class QrCodeUtil {
    private static final Logger LOG = Logger.getLogger(QrCodeUtil.class.getSimpleName());

    private static String encodeType = "UTF-8";
    private static int width = 1;
    private static int height = 1;
    private static int minSize = -1;

    /**
     * 生成二维码到指定文件
     *
     * @param content    二维码内容
     * @param targetFile 输出路径
     * @return 成功或失败
     */
    public static boolean encode(String content, File targetFile) {
        try {
            BufferedImage image = encode(content);
            if (image != null) ImageIO.write(image, "png", targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.warning("qwe");
            return false;
        }
        return true;
    }

    /**
     * 生成二维码
     *
     * @param content 二维码内容
     * @return 二维码图片
     */
    public static BufferedImage encode(String content) throws WriterException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, encodeType);
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix;
        BufferedImage zoomImage = null;
        bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 0, 0, hints);
        if (bitMatrix != null) {
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
            if (minSize != -1) {
                int k;
                int i = 1;
                do {
                    k = bitMatrix.getHeight() * i++;
                } while (k < minSize);
                zoomImage = ImageUtil.zoomImage(image, k, k);
                minSize = 0;
            } else
                zoomImage = ImageUtil.zoomImage(image, width, height);
            Result decode = decode(zoomImage);
            if (decode == null) {
                minSize = width > height ? width : height;
                zoomImage = encode(content);
            }
        }
        return zoomImage;
    }

    /**
     * 识别图片中的二维码
     *
     * @param file 要识别的图片
     * @return 二维码信息，若识别失败则返回null
     */
    public static String decode(File file) {
        BufferedImage image;
        Result result = null;
        try {
            image = ImageIO.read(file);
            result = decode(image);
        } catch (Exception ignored) {
        }
        return result != null ? result.getText() : null;
    }

    private static Result decode(BufferedImage image) {
        Result result = null;
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, encodeType);
            result = new MultiFormatReader().decode(binaryBitmap, hints);
        } catch (Exception ignored) {
        }
        return result;
    }

    public static void setSize(int width, int height) {
        if (width <= 0) width = 1;
        if (height <= 0) height = 1;
        QrCodeUtil.width = width;
        QrCodeUtil.height = height;
    }

    public static void setMinSize(int minSize) {
        QrCodeUtil.minSize = minSize;
    }
}
