package top.gjp0609.webtools.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author guojinpeng
 * @date 17.8.22 18:27
 */
public class StringUtil {

    public enum RaindomType {
        LOWER("纯小写字母", "abcdefghijklmnopqrstuvwxyz"),
        UPPER("纯大写字母", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        NUMBER("纯数字", "0123456789");

        private String name;
        private String value;

        RaindomType(String name, String index) {
            this.name = name;
            this.value = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    /**
     * 产生指定位数随机字符串
     *
     * @param length 长度
     * @param types  字符串类型
     * @return 随机码
     * @date 17.8.22
     */
    public static String getRandomString(int length, RaindomType... types) {
        // 生成源
        StringBuilder src = new StringBuilder();
        for (RaindomType type : types) {
            src.append(type.value);
        }
        Random random = new Random((long) (Math.random() * 1000));
        String string = "";
        int i;
        for (int j = 0; j < length; j++) {
            i = random.nextInt(src.length());
            string = string.concat(String.valueOf(src.charAt(i)));
        }
        return string;
    }

    /**
     * 获取去除短横线的随机UUID
     *
     * @return 32位uuid
     */
    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getString(Object object) {
        if (object == null) return "NULL";
        if (object instanceof Date)
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) object);
        return object.toString();
    }
}
