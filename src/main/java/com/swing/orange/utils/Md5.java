package com.swing.orange.utils;

import org.springframework.util.DigestUtils;

// MD5消息摘要算法
public class Md5 {
    public static String getMd5(String value ,String salt) {
        String base = value + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
