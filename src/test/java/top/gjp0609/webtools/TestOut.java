package top.gjp0609.webtools;

import java.util.Collection;

public class TestOut {
    public static void main(String[] args) {
        System.out.println(getValue(1L));
        System.out.println(getValue(123.234f));
        System.out.println(getValue(12313.234234));
        System.out.println(getValue("sf"));
        System.out.println(getValue('7'));
        System.out.println(getValue(0x21231f123b12L));
    }

    private static String getValue(Object o) {
        if (o instanceof Number || o instanceof CharSequence || o instanceof Comparable)
            return String.valueOf(o);
        else if (o instanceof Collection){

        }
        return "";
    }
}
