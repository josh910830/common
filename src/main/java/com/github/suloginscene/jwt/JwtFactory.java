package com.github.suloginscene.jwt;

import com.github.suloginscene.property.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.github.suloginscene.jwt.Base64Utils.encoded;
import static io.jsonwebtoken.SignatureAlgorithm.HS256;


@Component
public class JwtFactory {

    private static final long MINUTE = 60 * 1000L;

    private final JwtBuilder jwtBuilder;
    private final int expMin;


    JwtFactory(SecurityProperties securityProperties) {
        jwtBuilder = Jwts.builder().signWith(HS256, encoded(securityProperties.getSecret()));
        expMin = securityProperties.getExpMin();
    }


    public String create(Long audience) {
        Claims claims = Jwts.claims()
                .setAudience(audience.toString());

        Date now = new Date();
        Date exp = new Date(now.getTime() + expMin * MINUTE);

        return jwtBuilder
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .compact();
    }

}
