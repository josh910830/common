package com.github.suloginscene.security;

import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

import static com.github.suloginscene.security.Authorities.MEMBER;


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


    Authentication authentication() {
        return new UsernamePasswordAuthenticationToken(
                this, "", Set.of(new SimpleGrantedAuthority(MEMBER)));
    }

}
