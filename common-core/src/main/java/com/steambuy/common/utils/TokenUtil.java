package com.steambuy.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TokenUtil {
    private static Long timeLimit = 1000 * 60 * 60 * 24L;// 1天

    // 生成token
    public static String createToken(String subject, Map<String, Object> map, String secretKey){
        try {
            byte[] bytes = Base64.encodeBase64(secretKey.getBytes("utf-8"));
            String userToken = createToken(subject, map, bytes);
            return userToken;
        } catch (Exception e) {
            log.error("createToken error",e);
        }
        return null;
    }

    private static String createToken(String subject, Map<String, Object> map, byte[] secretKey) {
        String userToken = null;
        JwtBuilder builder = Jwts.builder().setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + timeLimit));
        if (map != null) {
            for (String key : map.keySet()) {
                builder.claim(key, map.get(key));
            }
        }
        userToken = builder.signWith(SignatureAlgorithm.HS512, secretKey).compact();

        return userToken;
    }

    /**
     * 解密 jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt, String secretKey) throws Exception {
        byte[] bytes = Base64.encodeBase64(secretKey.getBytes("utf-8"));
        Claims claims = Jwts.parser().setSigningKey(bytes).parseClaimsJws(jwt).getBody();
        return claims;
    }

    public static void main(String[] args) throws Exception {
        Map<String,Object> map=new HashMap<>();
        map.put("balance","100");

        String token = TokenUtil.createToken("zhangsan", map, "dasd");
        System.out.println(token);
        Claims dasd = TokenUtil.parseJWT(token, "dasd");
        System.out.println(dasd.getSubject());
        String balance =(String) dasd.get("balance");
        System.out.println(balance);

    }

}