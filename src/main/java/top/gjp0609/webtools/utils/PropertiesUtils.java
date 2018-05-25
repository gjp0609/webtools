package top.gjp0609.webtools.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author gojinpeng
 * @date 17.9.18 10:46
 */
public class PropertiesUtils {
    /**
     * 缓存的配置信息
     */
    private static Map<String, Properties> propertiesMap = null;


    public static String getProperty(String fileName, String propertyName) {
        loadFile(fileName);
        return propertiesMap.get(propertyName).getProperty(propertyName);
    }

    public static List<String> getProperties(String fileName, String propertyName) {
        loadFile(fileName);
        String property = propertiesMap.get(fileName).getProperty(propertyName);
        String[] strings = property.split(",");
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, strings);
        return list;
    }

    private static void loadFile(String fileName) {
        if (propertiesMap == null) {
            propertiesMap = new HashMap<>();
            InputStream inputStream = PropertiesUtils.class.getResourceAsStream("/" + fileName);
            try {
                Properties properties = new Properties();
                properties.load(inputStream);
                propertiesMap.put(fileName, properties);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
