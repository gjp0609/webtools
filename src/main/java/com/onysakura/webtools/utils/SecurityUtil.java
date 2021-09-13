package com.onysakura.webtools.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

    public static String md5(String source) {
        byte[] secretBytes;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(source.getBytes());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code.insert(0, "0");
        }
        return md5code.toString();
    }
}
