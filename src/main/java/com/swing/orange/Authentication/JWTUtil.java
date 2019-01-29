package com.swing.orange.Authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

public class JWTUtil {

    private static final Logger loger = LoggerFactory.getLogger(JWTUtil.class);

    // 过期时间60分钟
    private static final long EXPIRE_TIME = 60 * 60 * 1000;
    private static final String claimKey = "uid";

    /**
     * 生成签名,5min后过期
     *
     * @param uid 用户名
     * @param secret 用户的密码
     * @return 加密的token
     */
    public static String sign(String uid, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 附带uid信息
        return JWT.create()
                .withClaim(claimKey, uid)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String uid, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim(claimKey, uid)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return true;
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
