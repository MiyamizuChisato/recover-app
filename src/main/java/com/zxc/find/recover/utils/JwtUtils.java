package com.zxc.find.recover.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @author Miya
 */

public class JwtUtils {
    private static final String SING = "AO!ZHANG&XIAO&CHENG";

    public static String getToken(Map<String, String> map) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });
        String token = builder
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SING));
        return token;
    }

    public static DecodedJWT verity(String token) {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}
