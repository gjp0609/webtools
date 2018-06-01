package top.gjp0609.webtools.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author guojinpeng
 * @date 17.12.26 17:41
 */
public class ColorLog {

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
