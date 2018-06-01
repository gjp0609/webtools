package top.gjp0609.webtools.utils;//package me.rainbow.utils;


import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author guojinpeng
 * @date 17.10.16 10:46
 */
@SuppressWarnings({"unchecked", "unused", ""})
public class DebugUtil {

    public static void println(Object o) {
        String string = StringUtil.getString(o);
        String formattedDate = DateUtil.getFormattedDate();
        String colorStr = ColorString.getColorString(formattedDate + " " + string, Font.Color.DARK_GRAY);
        System.out.println(colorStr);
    }

    public static void println(Object o, Font.Color color) {
        String string = StringUtil.getString(o);
        String formattedDate = DateUtil.getFormattedDate();
        String colorStr = ColorString.getColorString(formattedDate + " " + string, color);
        System.out.println(colorStr);
    }

    public static void printMap(Map<String, Object> map) {
        System.out.print("\033[32m");
        System.out.println("map: ");
        if (map == null) {
            System.out.println("    null");
        } else if (map.size() == 0) {
            System.out.println("    empty");
        } else {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println("    " + entry.getKey() + ": " + entry.getValue());
            }
        }
        System.out.print("\033[0m");
    }

    public static <T> void printTMap(Map<String, T> map) {
        System.out.print("\033[32m");
        if (map == null) {
            System.out.println("    null");
        } else if (map.size() == 0) {
            System.out.println("    empty");
        } else {
            for (Map.Entry<String, T> entry : map.entrySet()) {
                System.out.println("map: ");
                System.out.print("    " + entry.getKey() + ": ");
                if (entry.getValue() == null) {
                    System.out.println("Object : null");
                } else {
                    System.out.println("Object " + entry.getValue().getClass().getSimpleName() + ": ");
                    Method[] methods = entry.getValue().getClass().getMethods();
                    for (Method method : methods) {
                        if (method.getName().startsWith("get")) {
                            try {
                                Object invoke = method.invoke(entry.getValue());
                                System.out.println("        " + method.getName() + ": " + invoke);
                            } catch (IllegalAccessException | InvocationTargetException ignored) {
                            }
                        }
                    }
                }
            }
        }
        System.out.print("\033[0m");
    }

    public static void printStringMap(Map<String, String> map) {
        System.out.print("\033[32m");
        System.out.println("Map: ");
        if (map == null) {
            System.out.println("    null");
        } else if (map.size() == 0) {
            System.out.println("    empty");
        } else {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println("    " + entry.getKey() + ": " + entry.getValue());
            }
        }
        System.out.print("\033[0m");
    }

    public static void printList(List list) {
        System.out.print("\033[34m");
        System.out.println("List: ");
        if (list == null) {
            System.out.println("    null");
        } else if (list.size() == 0) {
            System.out.println("    empty");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("    " + i + ": " + list.get(i));
            }
        }
        System.out.print("\033[0m");
    }

    public static <X> void printTList(List<X> list, String... names) {
        System.out.print("\033[34m");
        if (list == null) {
            System.out.println("List: null");
        } else {
            X temp = (X) new Object();
            for (X x : list) {
                if (x != null) temp = x;
            }
            if (list.size() == 0) {
                System.out.println("List: empty");
            } else {
                System.out.println("List<" + temp.getClass().getSimpleName() + ">: ");
                System.out.println("length: " + list.size());
                for (int i = 0; i < list.size(); i++) {
                    X t = list.get(i);
                    int printLine = 0;
                    System.out.println("    " + i + ": ");
                    if (t == null) {
                        printLine++;
                        System.out.println("        null");
                    } else {
                        for (Method method : t.getClass().getMethods()) {
                            String methodName = method.getName();
                            if (methodName.startsWith("get") && !"getClass".equals(methodName)) {
                                if (method.getParameterTypes().length <= 0) {
                                    ArrayList<String> arrayList = new ArrayList<>();
                                    Collections.addAll(arrayList, names);
                                    String name = methodName.substring(3);
                                    String c = String.valueOf(name.charAt(0));
                                    String lowerCase = c.toLowerCase();
                                    name = lowerCase.concat(name.substring(1));
                                    if (names.length == 0 || arrayList.contains(name)) {
                                        System.out.print("        " + name + ": ");
                                        try {
                                            X invoke = (X) method.invoke(t);
                                            printLine++;
                                            System.out.println(invoke);
                                        } catch (IllegalAccessException | InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (printLine == 0)
                        System.out.println("        " + t);
                }
            }
        }
        System.out.print("\033[0m");
    }

    public static <T> String printEntity(T t, Font.Color color) {
        ColorString string = new ColorString();
        if (t == null) {
            string.append("Entity : null");
            string.addType(Font.Type.BOLD);
        } else {
            Class<?> tClass = t.getClass();
            string.append("Entity " + tClass.getSimpleName() + ": ");
            Method[] methods = tClass.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("get") && !"getClass".equals(methodName)) {
                    String fieldName;
                    try {
                        char c = methodName.charAt(3);
                        String s = StringUtils.lowerCase(String.valueOf(c));
                        fieldName = s + methodName.substring(4);
                        Field field = tClass.getDeclaredField(fieldName);
                    } catch (NoSuchFieldException e) {
                        continue;
                    }
                    try {
                        Object invoke = method.invoke(t);
                        string.append("\n    " + fieldName + ": " + invoke);
                    } catch (IllegalAccessException | InvocationTargetException ignored) {
                    }
                }
            }
        }
        return string.setColor(color).toString();
    }

    private static String reflectionToString(Object obj) {
        if (obj == null) return "NULL";

        Class<?> objClass = obj.getClass();
        if (objClass.isPrimitive()) return String.valueOf(obj);

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
                String itemString = reflectionToString(arrayItem);
                sb.append(itemString);
            }
            return sb.append("]").toString();
        }

        // String
        if (obj instanceof CharSequence) {
            return "\"" + obj + "\"";
        }

        // List Set
        if (obj instanceof Iterable) {
            StringBuilder sb = new StringBuilder("[");
            Iterable iterable = (Iterable) obj;
            iterable.forEach((item) -> sb.append(reflectionToString(item)).append(","));
            return sb.substring(0, sb.length() - 1) + "]";
        }

        // Map
        if (obj instanceof Map) {
            StringBuilder sb = new StringBuilder("{");
            Map map = (Map) obj;
            map.forEach((k, v) -> sb.append(k).append(":").append(v).append(","));
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
                    sb.append(field.getName()).append(":").append(reflectionToString(o)).append(",");
                }
            }
            return sb.substring(0, sb.length() - 1) + "}";
        }

        System.out.println(ColorString.getColorString(objClass.getSimpleName(), Font.Color.CYAN));
        return String.valueOf(obj);
    }

    public static void main(String[] args) {

//        Stu stu = new Stu("1111", "2222", null);
//        Stu stu1 = new Stu("3333", "4444", stu);
//        Stu stu2 = new Stu("5555", "66666", stu1);
//        ArrayList<Stu> list = new ArrayList<>();
//        list.add(stu);
//        list.add(stu1);
//        list.add(stu2);
////        String s = ToStringBuilder.reflectionToString(stu2);
////        ColorString string = new ColorString(s);
////        string.addType(Font.Type.BOLD);
////        printEntity(stu2);
//        System.out.println(formatJson(reflectionToString(list)));
//        System.out.println(reflectionToString(LoggerFactory.getLogger("sad")));

    }

    /**
     * 对json字符串格式化输出
     *
     * @param jsonStr
     * @return
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
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
     *
     * @param sb
     * @param indent
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }

}

//@Entity
//class Stu {
//    private String s;
//    private String n;
//    private Stu stu;
//
//    public String getS() {
//        return s;
//    }
//
//    public void setS(String s) {
//        this.s = s;
//    }
//
//    public String getN() {
//        return n;
//    }
//
//    public void setN(String n) {
//        this.n = n;
//    }
//
//    public Stu getStu() {
//        return stu;
//    }
//
//    public void setStu(Stu stu) {
//        this.stu = stu;
//    }
//
//    public Stu() {
//    }
//
//    public Stu(String s, String n, Stu stu) {
//        this.s = s;
//        this.n = n;
//        this.stu = stu;
//    }
//
////    @Override
////    public String toString() {
////        return "Stu{" +
////                "s='" + s + '\'' +
////                ", n='" + n + '\'' +
////                ", stu=" + stu +
////                '}';
////    }
//}