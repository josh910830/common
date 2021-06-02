package com.github.suloginscene.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Null - UserDetailsService")
class NullUserDetailsServiceTest {

    @Test
    @DisplayName("load - null 반환")
    void loadUserByUsername_returnsNull() {
        UserDetailsService userDetailsService = new NullUserDetailsService();

        UserDetails userDetail = userDetailsService.loadUserByUsername("username");

        assertThat(userDetail).isNull();
    }

}
