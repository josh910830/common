package com.github.suloginscene.jwt;

import com.github.suloginscene.property.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import static com.github.suloginscene.jwt.Base64Utils.encoded;


public class JwtReader {

    private final JwtParser jwtParser;


    JwtReader(SecurityProperties securityProperties) {
        jwtParser = Jwts.parser().setSigningKey(encoded(securityProperties.getSecret()));
    }


    public String getAudience(String jwt) throws InvalidJwtException {
        return getPayload(jwt).getAudience();
    }

    private Claims getPayload(String jwt) throws InvalidJwtException {
        try {
            return jwtParser.parseClaimsJws(jwt).getBody();
        } catch (JwtException e) {
            String message = selectMessageByClass(e);
            throw new InvalidJwtException(message);
        }
    }

    private String selectMessageByClass(JwtException e) {
        switch (e.getClass().getSimpleName()) {
            case "ExpiredJwtException":
                return "Expired Jwt";
            case "SignatureException":
                return "Invalid Signature";
            case "MalformedJwtException":
                return "Malformed Jwt";
            default:
                return "Invalid Jwt";
        }
    }

}
