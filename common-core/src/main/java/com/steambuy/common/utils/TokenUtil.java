package com.steambuy.common.utils;

import com.steambuy.common.model.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class TokenUtil {

    private static final String secretKey="b38a421933fd49148c24221558ff7c98b371752a69784f70b9445245276c8783d3ca7b5b2d344d28af47ecfba7e969b5a443959f320049039460b76d8431aacd";

    public static String createToken(UserInfo userInfo){
        long timeLimit = 1000 * 60 * 60 * 24L;// 1天
        return Jwts.builder().setId(String.valueOf(userInfo.getId())).setSubject(userInfo.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + timeLimit))
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    public static UserInfo parseToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return new UserInfo(Long.valueOf(claims.getId()),claims.getSubject());
    }

}