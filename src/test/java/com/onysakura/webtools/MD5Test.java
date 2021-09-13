package com.onysakura.webtools;

import com.onysakura.webtools.utils.SecurityUtil;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Scanner;

public class MD5Test {

    public static void main(String[] args) {
        System.out.println("input password: ");
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();

        String salt = RandomStringUtils.randomAlphanumeric(32);
        System.out.println("password: " + password);
        System.out.println("salt: " + salt);
        System.out.println("passwordDB: " + SecurityUtil.md5(SecurityUtil.md5(password) + SecurityUtil.md5(salt.toString())));
    }
}
