package com.swing.orange.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class Signature {

    // 签名参数的key
    public static final String SIGN_KEY = "signature_secret";
    // ACCESS_KEY
    private static final String ACCESS_KEY = "accessKey";
    // ACCESS_SECRET
    private static final String ACCESS_SECRET = "!@#orange";
    // md5盐值，用于混淆
    private final static String signSalt = "orange!@#";

    // 验证签名
    public static boolean verificationSign(String originSign, Map<String, Object> params) throws Exception {
        String sign = createSign(params);
        return sign.equals(originSign);
    }

    // 签名
    public static String createSign(Map<String, Object> params) throws Exception {
        System.out.println("签名的参数" + params);
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuilder temp = new StringBuilder();
        for (Object key : keys) {
            temp.append(key);
            temp.append("&");
        }
        temp.append(ACCESS_KEY).append("=").append(ACCESS_SECRET);
        System.out.println("createSign temp：" + temp);
        String sign = Md5.getMd5(temp.toString(), signSalt);
        System.out.println("签名：" + sign);
        return sign;
    }
}
