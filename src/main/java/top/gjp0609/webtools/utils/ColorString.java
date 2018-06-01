package top.gjp0609.webtools.utils;

public class ColorString {

    private final static String SUFFIX = "\033[0m";

    private String string = "";
    private int[] types = {0, 0, 0};
    private int background = 0;
    private int color = 0;

    public ColorString() {
    }

    public ColorString(String string) {
        this.string = string;
    }

    public ColorString setString(String string) {
        this.string = string;
        return this;
    }

    public ColorString append(String string) {
        this.string += string;
        return this;
    }

    public ColorString setColor(Font.Color color) {
        this.color = color.getColorValue();
        return this;
    }

    public ColorString setBackgroundColor(Font.Color color) {
        this.background = color.getBackgroundColorValue();
        return this;
    }

    public ColorString addType(Font.Type type) {
        switch (type.getTypeValue()) {
            case 1:
                this.types[0] = 1;
                break;
            case 4:
                this.types[1] = 4;
                break;
            case 7:
                this.types[2] = 7;
                break;
            default:
                break;
        }
        return this;
    }

    private static String getStartSymbol(int value) {
        return value == 0 ? "" : "\033[" + value + "m";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i : types) {
            sb.append(getStartSymbol(i));
        }
        return sb.append(getStartSymbol(background))
                .append(getStartSymbol(color))
                .append(string)
                .append(SUFFIX)
                .toString();
    }

    public static String getColorString(String str, Font.Color color) {
        return getStartSymbol(color.getColorValue()) + str + SUFFIX;
    }

    public static String getColorString(String str, Font.Type... types) {
        return getColorString(str, null, null, types);
    }

    public static String getColorString(String str,
                                        Font.Color color,
                                        Font.Color backgroundColor,
                                        Font.Type... types) {
        StringBuilder sb = new StringBuilder();
        for (Font.Type type : types) {
            sb.append(getStartSymbol(type.getTypeValue()));
        }
        return sb.append(color == null ? "" : getStartSymbol(color.getColorValue()))
                .append(backgroundColor == null ? "" : getStartSymbol(backgroundColor.getColorValue()))
                .append(str)
                .append(SUFFIX)
                .toString();
    }
}