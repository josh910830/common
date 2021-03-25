package com.github.suloginscene.jwtconfig;

import com.github.suloginscene.jjwthelper.InvalidJwtException;
import com.github.suloginscene.jjwthelper.JwtReader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
class JwtAuthenticator {

    private final JwtReader jwtReader;


    public void authenticate(String jwt) throws InvalidJwtException {
        if (isExistent(jwt)) {
            Authentication authentication = toAuthentication(jwt);
            setAuthentication(authentication);
        }
    }

    private boolean isExistent(String jwt) {
        return jwt != null && !jwt.isBlank();
    }

    private Authentication toAuthentication(String token) throws InvalidJwtException {
        String audience = jwtReader.getAudience(token);
        Principal principal = Principal.of(audience);
        return principal.token();
    }

    private void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
