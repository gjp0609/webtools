package top.gjp0609.webtools.utils;

import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /**
     * MD5散列
     */
    public static String md5Crypt(byte[] keyBytes) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(keyBytes);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            return byteToHexStr(md);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * MD5散列
     */
    public static String md5Crypt(byte[] keyBytes, String salt) {
        String strKey = new String(keyBytes);
        if (salt != null && !"".equals(salt)) strKey = strKey + "{" + salt + "}";
        return md5Crypt(strKey.getBytes());
    }

    /**
     * SHA1散列
     */
    public static String sha1Crypt(byte[] keyBytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(keyBytes);
            return byteToHexStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * SHA1散列
     */
    public static String sha1Crypt(byte[] keyBytes, String salt) {
        String fullStr = new String(keyBytes) + "{" + salt + "}";
        return sha1Crypt(fullStr.getBytes());
    }

    /**
     * 将byte数组变为16进制对应的字符串
     *
     * @param byteArray byte数组
     * @return 转换结果
     */
    private static String byteToHexStr(byte[] byteArray) {
        StringBuilder hexString = new StringBuilder();
        for (byte aByteArray : byteArray) {
            String shaHex = Integer.toHexString(aByteArray & 0xFF);
            if (shaHex.length() < 2) hexString.append(0);
            hexString.append(shaHex);
        }
        return hexString.toString();
    }

    /**
     * MD5编码
     *
     * @param origin      原始字符串
     * @param charsetName 字符编码
     * @return 经过MD5加密之后的结果
     */
    public static String MD5Encode(String origin, String charsetName) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (StringUtils.isNotBlank(charsetName))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    public static String MD5Encode(String origin) {
        return MD5Encode(origin, "UTF-8");
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) resultSb.append(byteToHexString(aB));
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) n += 256;
        return hexDigits[n / 16] + hexDigits[n % 16];
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}