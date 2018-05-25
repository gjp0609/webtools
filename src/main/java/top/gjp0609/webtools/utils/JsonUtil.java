package top.gjp0609.webtools.utils;//package me.rainbow.utils;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author guojinpeng
// * @date 17.8.24 18:06
// */
//public class JsonUtil {
//    public static List<Map<String, Object>> parstToList(JSONArray array) {
//        Object[] objects = array.toArray();
//        ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
//        for (Object o : objects) {
//            Map<String, Object> map = (Map<String, Object>) o;
//            arrayList.add(map);
//        }
//        return arrayList;
//    }
//
//    public static void main(String[] args) {
//        JSONArray array = new JSONArray();
//        JSONObject json1 = new JSONObject();
//        json1.put("name", "zs");
//        json1.put("age", "18");
//        json1.put("phone", "12423423");
//        array.add(json1);
//        JSONObject json2 = new JSONObject();
//        json2.put("name", "ls");
//        json2.put("age", "22");
//        json2.put("phone", "3425345");
//        array.add(json2);
//        System.out.println(array.toString());
//        List<Map<String, Object>> mapList = parstToList(array);
//        System.out.println(String.valueOf(mapList));
//    }
//}
