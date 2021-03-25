package com.github.suloginscene.jwtconfig;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static java.util.Collections.emptySet;


@Data
class Principal {

    private final Long memberId;


    private Principal(Long memberId) {
        this.memberId = memberId;
    }

    static Principal of(String audience) {
        long memberId = Long.parseLong(audience);
        return new Principal(memberId);
    }


    UsernamePasswordAuthenticationToken token() {
        return new UsernamePasswordAuthenticationToken(this, "", emptySet());
    }

}
