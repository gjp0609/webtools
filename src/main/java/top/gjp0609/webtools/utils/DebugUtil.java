package top.gjp0609.webtools.utils;//package me.rainbow.utils;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * @author guojinpeng
// * @date 17.10.16 10:46
// */
//@SuppressWarnings({"unchecked", "unused", ""})
//public class DebugUtil {
//
//    public static void println(Object o) {
//        String string = StringUtil.getString(o);
//        String formattedDate = DateUtil.getFormattedDate(DateUtil.now());
//        String colorStr = ColorUtil.getColorStr(formattedDate + " " + string, Color.GRAY);
//        System.out.println(colorStr);
//    }
//
//    public static void println(Object o, Color color) {
//        String string = StringUtil.getString(o);
//        String formattedDate = DateUtil.getFormattedDate(DateUtil.now());
//        String colorStr = ColorUtil.getColorStr(formattedDate + " " + string, color);
//        System.out.println(colorStr);
//    }
//
//    public static void printMap(Map<String, Object> map) {
//        System.out.print("\033[32m");
//        System.out.println("map: ");
//        if (map == null) {
//            System.out.println("    null");
//        } else if (map.size() == 0) {
//            System.out.println("    empty");
//        } else {
//            for (Map.Entry<String, Object> entry : map.entrySet()) {
//                System.out.println("    " + entry.getKey() + ": " + entry.getValue());
//            }
//        }
//        System.out.print("\033[0m");
//    }
//
//    public static <T> void printTMap(Map<String, T> map) {
//        System.out.print("\033[32m");
//        if (map == null) {
//            System.out.println("    null");
//        } else if (map.size() == 0) {
//            System.out.println("    empty");
//        } else {
//            for (Map.Entry<String, T> entry : map.entrySet()) {
//                System.out.println("map: ");
//                System.out.print("    " + entry.getKey() + ": ");
//                if (entry.getValue() == null) {
//                    System.out.println("Object : null");
//                } else {
//                    System.out.println("Object " + entry.getValue().getClass().getSimpleName() + ": ");
//                    Method[] methods = entry.getValue().getClass().getMethods();
//                    for (Method method : methods) {
//                        if (method.getName().startsWith("get")) {
//                            try {
//                                Object invoke = method.invoke(entry.getValue());
//                                System.out.println("        " + method.getName() + ": " + invoke);
//                            } catch (IllegalAccessException | InvocationTargetException ignored) {
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        System.out.print("\033[0m");
//    }
//
//    public static void printStringMap(Map<String, String> map) {
//        System.out.print("\033[32m");
//        System.out.println("Map: ");
//        if (map == null) {
//            System.out.println("    null");
//        } else if (map.size() == 0) {
//            System.out.println("    empty");
//        } else {
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                System.out.println("    " + entry.getKey() + ": " + entry.getValue());
//            }
//        }
//        System.out.print("\033[0m");
//    }
//
//    public static void printList(List list) {
//        System.out.print("\033[34m");
//        System.out.println("List: ");
//        if (list == null) {
//            System.out.println("    null");
//        } else if (list.size() == 0) {
//            System.out.println("    empty");
//        } else {
//            for (int i = 0; i < list.size(); i++) {
//                System.out.println("    " + i + ": " + list.get(i));
//            }
//        }
//        System.out.print("\033[0m");
//    }
//
//    public static <X> void printTList(List<X> list, String... names) {
//        System.out.print("\033[34m");
//        if (list == null) {
//            System.out.println("List: null");
//        } else {
//            X temp = (X) new Object();
//            for (X x : list) {
//                if (x != null) temp = x;
//            }
//            if (list.size() == 0) {
//                System.out.println("List: empty");
//            } else {
//                System.out.println("List<" + temp.getClass().getSimpleName() + ">: ");
//                System.out.println("length: " + list.size());
//                for (int i = 0; i < list.size(); i++) {
//                    X t = list.get(i);
//                    int printLine = 0;
//                    System.out.println("    " + i + ": ");
//                    if (t == null) {
//                        printLine++;
//                        System.out.println("        null");
//                    } else {
//                        for (Method method : t.getClass().getMethods()) {
//                            String methodName = method.getName();
//                            if (methodName.startsWith("get") && !"getClass".equals(methodName)) {
//                                if (method.getParameterTypes().length <= 0) {
//                                    ArrayList<String> arrayList = new ArrayList<>();
//                                    Collections.addAll(arrayList, names);
//                                    String name = methodName.substring(3);
//                                    String c = String.valueOf(name.charAt(0));
//                                    String lowerCase = c.toLowerCase();
//                                    name = lowerCase.concat(name.substring(1));
//                                    if (names.length == 0 || arrayList.contains(name)) {
//                                        System.out.print("        " + name + ": ");
//                                        try {
//                                            X invoke = (X) method.invoke(t);
//                                            printLine++;
//                                            System.out.println(invoke);
//                                        } catch (IllegalAccessException | InvocationTargetException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    if (printLine == 0)
//                        System.out.println("        " + t);
//                }
//            }
//        }
//        System.out.print("\033[0m");
//    }
//
//    public static <T> void printObject(T t) {
//        System.out.print("\033[35m");
//        if (t == null) {
//            System.out.println("Object : null");
//        } else {
//            System.out.println("Object " + t.getClass().getSimpleName() + ": ");
//            Method[] methods = t.getClass().getMethods();
//            for (Method method : methods) {
//                if (method.getName().startsWith("get")) {
//                    try {
//                        Object invoke = method.invoke(t);
//                        System.out.println("    " + method.getName() + ": " + invoke);
//                    } catch (IllegalAccessException | InvocationTargetException ignored) {
//                    }
//                }
//            }
//        }
//        System.out.print("\033[0m");
//    }
//}
