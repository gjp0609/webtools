package top.gjp0609.webtools.utils;//package me.rainbow.utils;


import javax.persistence.Entity;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author guojinpeng
 * @date 17.10.16 10:46
 */
@SuppressWarnings({"unchecked", "unused", ""})
public class DebugUtil {

    private static int level = 10;
    private static String replace = "...";

    public static void setShowLevel(int level) {
        DebugUtil.level = level;
    }

    public static void setReplace(String replace) {
        DebugUtil.replace = replace;
    }

    public static String getFmtRefStr(Object obj) {
        return formatJson(reflectionToString(obj));
    }

    public static String getFmtRefStr(Object obj, int index) {
        return formatJson(reflectionToString(obj, index));
    }

    public static String reflectionToString(Object obj) {
        return reflectionToString(obj, 0);
    }

    private static String reflectionToString(Object obj, final int index) {

        if (obj == null) {
            return "NULL";
        }

        if (index >= level) return replace;

        Class<?> objClass = obj.getClass();
        if (objClass.isPrimitive()) {
            return String.valueOf(obj);
        }

        if (objClass == Object.class) {
            return obj.toString();
        }

        // Array
        if (objClass.isArray()) {
            StringBuilder sb = new StringBuilder("[");
            final int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                if (i > 0) sb.append(",");
                Object arrayItem = Array.get(obj, i);
                String itemString = reflectionToString(arrayItem, index + 1);
                sb.append(itemString);
            }
            return sb.append("]").toString();
        }

        // Date
        if (obj instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            return sdf.format(obj);
        }

        // String
        if (obj instanceof CharSequence) {
            return "\"" + obj + "\"";
        }

        // List Set
        if (obj instanceof Iterable) {
            StringBuilder sb = new StringBuilder("[");
            Iterable iterable = (Iterable) obj;
            iterable.forEach((item) -> sb.append(reflectionToString(item, index + 1)).append(","));
            return sb.substring(0, sb.length() - 1) + "]";
        }

        // Map
        if (obj instanceof Map) {
            StringBuilder sb = new StringBuilder("{");
            Map map = (Map) obj;
            map.forEach((k, v) -> sb.append(k).append(":").append(reflectionToString(v, index + 1)).append(","));
            return sb.substring(0, sb.length() - 1) + "}";
        }

        // Entity
        if (objClass.getAnnotation(Entity.class) != null) {
            Field[] fields = objClass.getDeclaredFields();
            StringBuilder sb = new StringBuilder("{");
            for (Field field : fields) {
                String fieldGetName = "get" + StringUtil.getFirstUpperString(field.getName());
                Method method = null;
                try {
                    method = objClass.getMethod(fieldGetName);
                } catch (NoSuchMethodException ignored) {
                }
                if (method != null) {
                    Object o = null;
                    try {
                        o = method.invoke(obj);
                    } catch (IllegalAccessException | InvocationTargetException ignored) {
                    }
                    sb.append(field.getName()).append(":").append(reflectionToString(o, index + 1)).append(",");
                }
            }
            return sb.substring(0, sb.length() - 1) + "}";
        }
        System.out.println(ColorString.getColorString(objClass.getSimpleName(), Font.Color.CYAN));
        return String.valueOf(obj);
    }


    /**
     * 对json字符串格式化输出
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last;
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }
        return sb.toString();
    }

    /**
     * 添加space
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) sb.append('\t');
    }

}