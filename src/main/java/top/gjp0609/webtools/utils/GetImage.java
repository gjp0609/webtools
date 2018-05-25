package top.gjp0609.webtools.utils;//package me.rainbow.utils;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.*;
//
///**
// * @author guojinpeng
// * @date 17.11.7 15:10
// */
//public class GetImage {
//    public static void main(String[] args) {
////        Integer[] ss = new Integer[]{7, 13, 19, 23, 27, 31, 32, 61, 68, 71, 74, 89, 90, 115, 119, 122, 124, 125, 129, 130, 137, 138, 139, 143, 144, 145, 146, 147, 148, 149, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 169, 170, 173, 179, 196, 218, 235, 236};
////        ArrayList<Integer> arrayList = new ArrayList<>();
////        Collections.addAll(arrayList, ss);
//        Properties properties = new Properties();
//        try {
//            properties.load(new FileInputStream(new File("C:/Files/Data/test.properties")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        TreeMap<Integer, String> treeMap = new TreeMap<>();
//
//        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
//            Integer key = Integer.valueOf(String.valueOf(entry.getKey()));
////            if (arrayList.contains(key))
//                treeMap.put(key, String.valueOf(entry.getValue()));
//        }
//        ArrayList<Integer> list = new ArrayList<>();
//        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
//            System.out.println(entry.getKey());
//            try {
//                download(String.valueOf(entry.getKey()), entry.getValue());
//            } catch (Exception e) {
//                System.err.print(entry.getKey());
//                System.err.println(", " + e.getMessage());
//                list.add(entry.getKey());
//            }
//        }
//        for (Integer integer : list) {
//            System.out.print(integer + ", ");
//        }
//
//    }
//
//
//    public static void download(String dirName, String url) {
//        ArrayList<String> list = getHrefByNet(url);
//        ArrayList<String> urls = new ArrayList<>();
//        for (String o : list) {
//            if (o != null && o.contains("largeimage")) {
//                int index = o.lastIndexOf("http://image");
//                int lastIndex = o.lastIndexOf("'");
//                if (index > 0 && lastIndex > 0) {
//                    String substring = o.substring(index, lastIndex);
//                    urls.add(substring);
//                } else {
//                    int aindex = o.indexOf("http://image");
//                    if (aindex > 0) {
//                        String s = o.substring(aindex);
//                        int alastIndex = s.indexOf("'");
//                        if (alastIndex > 0) {
//                            String substring = s.substring(0, alastIndex);
//                            urls.add(substring);
//                        }
//                    }
//                }
//            }
//        }
//        File data = new File("C:/Files/Data/" + dirName);
//        data.mkdir();
//        int i = 0;
//        for (String u : urls) {
//            try {
//                downLoadFromUrl(u, i + ".jpg", data.getAbsolutePath());
//            } catch (Exception ignored) {
//            }
//            i++;
//        }
//    }
//
//    public static ArrayList<String> getHrefByNet(String url) {
//        ArrayList<String> list = new ArrayList<>();
//        try {
//            //这是get方式得到的
//            Document doc = Jsoup.connect(url).get();
//            Elements elements = doc.select("a");
//            for (Element element : elements) {
//                String rel = element.attr("rel");
//                list.add(rel);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>(new HashSet<>(list));
//    }
//
//
//    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
//        URL url = new URL(urlStr);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        //设置超时间为3秒
//        conn.setConnectTimeout(3 * 1000);
//        //防止屏蔽程序抓取而返回403错误
//        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//
//        //得到输入流
//        InputStream inputStream = conn.getInputStream();
//        //获取自己数组
//        byte[] getData = readInputStream(inputStream);
//
//        //文件保存位置
//        File saveDir = new File(savePath);
//        if (!saveDir.exists()) {
//            saveDir.mkdir();
//        }
//        File file = new File(saveDir + File.separator + fileName);
//        FileOutputStream fos = new FileOutputStream(file);
//        fos.write(getData);
//        if (fos != null) {
//            fos.close();
//        }
//        if (inputStream != null) {
//            inputStream.close();
//        }
//
//
//        System.out.println("info:" + url + " download success");
//
//    }
//
//
//    /**
//     * 从输入流中获取字节数组
//     *
//     * @param inputStream
//     * @return
//     * @throws IOException
//     */
//    public static byte[] readInputStream(InputStream inputStream) throws IOException {
//        byte[] buffer = new byte[1024];
//        int len = 0;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        while ((len = inputStream.read(buffer)) != -1) {
//            bos.write(buffer, 0, len);
//        }
//        bos.close();
//        return bos.toByteArray();
//    }
//
//}
