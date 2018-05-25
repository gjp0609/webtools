package top.gjp0609.webtools.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author guojinpeng
 * @date 17.12.13 17:48
 */
public class CollectionUtil {
    /**
     * 根据条件去除list中重复元素并且去除null
     *
     * @param list          要去除重复元素的list
     * @param getMethodName get方法的名字 eg:getId
     * @param first         重复元素保留规则【true:保留最先出现的, false:保留最后出现的】
     */
    public static <X> List<X> removeDuplicates(List<X> list, final String getMethodName, boolean first) {
        TreeSet<X> set = new TreeSet<>((x1, x2) -> {
            int i = 0;
            try {
                String v1 = String.valueOf(x1.getClass().getMethod(getMethodName).invoke(x1));
                String v2 = String.valueOf(x2.getClass().getMethod(getMethodName).invoke(x2));
                i = v1.compareTo(v2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return i;
        });
        for (X x : list)
            if (x != null)
                if (set.contains(x)) {
                    if (!first) set.add(x);
                } else set.add(x);
        return new ArrayList<>(set);
    }

    /**
     * 根据条件去除list中重复元素并且去除null
     *
     * @param list          要去除重复元素的list
     * @param getMethodName get方法的名字 eg:getId
     */
    public static <X> List<X> removeDuplicates(List<X> list, final String getMethodName) {
        return removeDuplicates(list, getMethodName, true);
    }
}
