package top.gjp0609.webtools.utils;

/**
 * @author guojinpeng
 * @date 17.9.29 18:06
 */

public enum Color {
    /**
     * 字型：<b>粗体</b>
     */
    BOLD("粗体", 1),
    /**
     * 字型：<u>下划线</u>
     */
    UNDERLINE("下划线", 4),
    /**
     * 颜色：<span color='#A8C023'>反色</span>
     */
    INVERT("反色", 7),
    /**
     * <span color='#FFFFFF'>白色</span>
     */
    WHITE("白色", 30),
    /**
     * <span color='#FF6B68'>红色</span>
     */
    RED("红色", 31),
    /**
     * <span color='#A8C023'>黄绿色</span>
     */
    GREEN_YELLOW("黄绿色", 32),
    /**
     * <span color='#A8C023'>黄绿色</span>
     */
    YELLOW("黄色", 33),
    /**
     * <span color='#A8C023'>黄绿色</span>
     */
    BLUE("蓝色", 34),
    /**
     * <span color='#A8C023'>黄绿色</span>
     */
    PURPLE("紫色", 35),
    /**
     * <span color='#A8C023'>紫色</span>
     */
    PU("青色", 36),
    /**
     * <span color='#999999'>灰色</span>
     */
    GRAY("灰色", 37),
    /**
     * <span color='#A8C023'>黄绿色</span>
     */
    BLACK_BACK("", 107);

    private String name;
    private int value;

    Color(String name, int index) {
        this.name = name;
        this.value = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
