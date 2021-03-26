package com.github.suloginscene.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import static com.github.suloginscene.jwt.Base64Utils.encoded;


public class JwtReader {

    private final JwtParser jwtParser;


    JwtReader(String secret) {
        jwtParser = Jwts.parser()
                .setSigningKey(encoded(secret));
    }


    public String getAudience(String jwt) throws InvalidJwtException {
        return getPayload(jwt).getAudience();
    }

    private Claims getPayload(String jwt) throws InvalidJwtException {
        try {
            return jwtParser.parseClaimsJws(jwt).getBody();
        } catch (JwtException e) {
            throw new InvalidJwtException(e.getClass());
        }
    }

}