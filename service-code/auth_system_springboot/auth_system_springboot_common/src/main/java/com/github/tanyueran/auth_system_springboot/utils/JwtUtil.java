package com.github.tanyueran.auth_system_springboot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt的加解密
 */
public class JwtUtil {
    // 使用 secret 加解密
    private static String SECRET = "1234567890987654321";
    private static String ISSUER = "sys_user";

    /**
     * 获取token
     *
     * @param claims          载荷
     * @param privateKey      私钥
     * @param expireDatePoint 过期时间
     * @return String token
     */
    public static String genToken(Map<String, String> claims, RSAPrivateKey privateKey, Date expireDatePoint) throws Exception {
        //使用HMAC256进行加密
//        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        // 私钥加密
        Algorithm algorithm = Algorithm.RSA256(null, privateKey);

        //创建jwt
        JWTCreator.Builder builder = JWT.create()
                .withIssuer(ISSUER)//签发人
                .withExpiresAt(expireDatePoint); //过期时间点

        //传入参数
        claims.forEach((key, value) -> {
            builder.withClaim(key, value);
        });

        //签名加密
        return builder.sign(algorithm);
    }

    /**
     * 验证token
     *
     * @param token     待验证的token
     * @param publicKey 公钥
     * @return
     */
    public static Map<String, String> verifyToken(String token, RSAPublicKey publicKey) throws Exception {
//        Algorithm algorithm = Algorithm.HMAC256(SECRET);//使用HMAC256进行加密
        Algorithm algorithm = Algorithm.RSA256(publicKey, null);

        //解密
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        // 获取载荷
        Map<String, Claim> map = jwt.getClaims();
        Map<String, String> resultMap = new HashMap<>();
        map.forEach((k, v) -> resultMap.put(k, v.asString()));
        return resultMap;
    }


    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("username", "张三");
        map.put("userCode", "tom");
        long time = new Date().getTime();
        time += 24 * 60 * 60 * 1000;
        Date date = new Date(time);
        String publicPath = "E:\\workspace\\idea\\key\\public.key";
        String privatePath = "E:\\workspace\\idea\\key\\private.key";
        String token = JwtUtil.genToken(map, (RSAPrivateKey) RsaUtil.getPrivateKey(privatePath), date);
        System.out.println(token);

        Map<String, String> map2 = JwtUtil.verifyToken(token, (RSAPublicKey) RsaUtil.getPublicKey(publicPath));
        System.out.println(map2);
    }
}
