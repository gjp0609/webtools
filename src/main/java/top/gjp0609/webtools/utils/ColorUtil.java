package top.gjp0609.webtools.utils;


import java.util.logging.Logger;

/**
 * @author guojinpeng
 * @date 17.9.29 18:05
 */
@SuppressWarnings("unused")
public class ColorUtil {
    private static final Logger log = Logger.getLogger(ColorUtil.class.getSimpleName());

    public static String getColorStr(String src, Color color) {
        return " \033[" + color.getValue() + "m" + src + "\033[0m";
    }

    /**
     * 得到彩色效果的字符串
     * 支持彩色输出的控制台
     * 可写入log得到彩色日志
     *
     * @param src 原字符串
     * @return 变色后的字符串
     */
    public static String getColorfulString(String src, Color... colors) {
        StringBuilder s = new StringBuilder();
        for (Color color : colors) {
            s.append("\033[");
            s.append(color.getValue());
            s.append("m");
        }
        s.append(src);
        s.append("\033[0m");
        return s.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) System.out.println(" \033[" + i + "mHello " + i + " 你好\033[0m");
        for (int i = 30; i < 50; i++) System.out.println(" \033[" + i + "mHello " + i + " 你好\033[0m");
        for (int i = 90; i < 110; i++) System.out.println(" \033[" + i + "mHello " + i + " 你好\033[0m");
    }
}
