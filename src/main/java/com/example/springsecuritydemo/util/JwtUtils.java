package com.example.springsecuritydemo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 *
 * @author Tragoedia
 */
@SuppressWarnings("unused")
public class JwtUtils {

    private JwtUtils() {
    }

    /**
     * 有效期一小时
     */
    public static final Long JWT_TTL = 60 * 60 * 1000L;

    /**
     * 设置秘钥明文8位倍数
     */
    public static final String JWT_KEY = "Security";

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成 jwt
     *
     * @param subject token中要存放的数据（json格式）
     * @return jwt
     */
    public static String createJwt(String subject) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, null, getUuid());
        return builder.compact();
    }

    /**
     * 生成jtw
     *
     * @param subject   token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return jwt
     */
    public static String createJwt(String subject, Long ttlMillis) {
        // 设置过期时间
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUuid());
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtils.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject)
                .setIssuer("Tragoedia")
                .setIssuedAt(now)
                //使用HS256对称加密算法签名, 第二个参数为秘钥
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expDate);
    }

    /**
     * 创建token
     *
     * @param uuid      uuid
     * @param subject   token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     */
    public static String createJwt(String uuid, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, uuid);
        return builder.compact();
    }

    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return key
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtils.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析
     *
     * @param jwt jwt
     * @return claims
     */
    public static Claims parseJwt(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}
