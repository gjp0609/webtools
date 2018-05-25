package top.gjp0609.webtools.utils;//package me.rainbow.utils;
//
//import org.apache.commons.lang3.time.DateUtils;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
///**
// * @author guojinpeng
// * @date 17.12.20 12:13
// */
//public class DateUtil {
//    /**
//     * 获取左上角日期
//     */
//    public static void getLastDayOfMonth() throws ParseException {
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//    }
//
//    /**
//     * 获取左上角日期
//     *
//     * @param yearAndMonth 格式 yyyy-MM
//     */
//    public static Date getLeftTopDate(String yearAndMonth) throws ParseException {
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = format.parse(yearAndMonth + "-01");
//        calendar.setTime(date);
//        int i = calendar.get(Calendar.DAY_OF_WEEK);
//        return DateUtils.addDays(date, -i + 1);
//    }
//
//    /**
//     * 获取右下角日期
//     *
//     * @param yearAndMonth 格式 yyyy-MM
//     */
//    public static Date getRightBottomDate(String yearAndMonth) throws ParseException {
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = format.parse(yearAndMonth + "-01");
//        calendar.setTime(date);
//        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//        date = calendar.getTime();
//        int i = calendar.get(Calendar.DAY_OF_WEEK);
//        System.out.println(i);
//        return DateUtils.addDays(date, 7 - i);
//    }
//
//    public static String getFormattedDate(Date date) {
//        if (date != null) return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
//        return "UNKNOWN DATE";
//    }
//
//    public static Date now() {
//        return new Date();
//    }
//}
