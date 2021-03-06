package com.swing.orange.Authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

public class JWTUtil {

    private static final Logger loger = LoggerFactory.getLogger(JWTUtil.class);
    private static final String claimKey = "uid";

    /**
     * 生成签名,5min后过期
     *
     * @param uid 用户名
     * @param secret 用户的密码
     * @param expireDays token过期天数
     * @return 加密的token
     */
    public static String sign(String uid, String secret, int expireDays) {
        long expireTime = expireDays * 24 * 60 * 60 * 1000;
        Date date = new Date(System.currentTimeMillis() + expireTime);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带uid信息
        String token = JWT.create()
                .withClaim(claimKey, uid)
                .withExpiresAt(date)
                .sign(algorithm);
        loger.info("token: " +token);
        return token;
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static int verify(String token, String uid, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim(claimKey, uid)
                .build();
        try {
            verifier.verify(token);
        } catch (TokenExpiredException ex) {
            return 1;
        } catch (JWTVerificationException ex) {
            return 2;
        }
        return 0;
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的uid
     */
    public static String getUid(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(claimKey).asString();
    }

}
