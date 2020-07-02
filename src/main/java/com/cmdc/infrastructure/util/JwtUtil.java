package com.cmdc.infrastructure.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cmdc.infrastructure.common.Constant;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 *需要注意的是 这边校验的方式并非非对称加密
 * 可以改造为非对称加密: 公钥加密，私钥解密 私钥生成签名，公钥验证签名
 */
@Slf4j
public class JwtUtil {
    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String secret) {
        try {
            //根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(Constant.TOKEN_CLAIM, getUserId(token))
                    .build();
            // 效验TOKEN
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }


    /**
     * 获得token中的信息无需secret解密也能获得
     * @return token中包含的用户名
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(Constant.TOKEN_CLAIM).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     *

     * @param userId   用户id
     * @param secret   制作此token的签名依据
     * @return 加密的token
     */
    public static String sign(String userId, String secret) {
        Date date = new Date(System.currentTimeMillis() + Constant.TOKEN_EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim(Constant.TOKEN_CLAIM, userId)
                .withExpiresAt(date)
                .sign(algorithm);

    }
}
