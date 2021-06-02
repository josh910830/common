package com.github.suloginscene.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DisplayName("프로퍼티 - 보안")
class SecurityPropertiesTest {

    SecurityProperties securityProperties;


    @BeforeEach
    void setup() {
        securityProperties = new SecurityProperties();
    }


    @Test
    @DisplayName("기본값")
    void defaultValues() {
        assertThat(securityProperties.getSecret()).isNotNull();
        assertThat(securityProperties.getOrigins()).isNotNull();
        assertThat(securityProperties.getExpMin()).isNotNull();
        assertThat(securityProperties.getRefreshExpDay()).isNotNull();
    }

    @Test
    @DisplayName("설정")
    void setValues() {
        securityProperties.setSecret("s");
        securityProperties.setOrigins("http://localhost:80");
        securityProperties.setExpMin(1);
        securityProperties.setRefreshExpDay(1);

        assertThat(securityProperties.getSecret()).isEqualTo("s");
        assertThat(securityProperties.getOrigins()).isEqualTo("http://localhost:80");
        assertThat(securityProperties.getExpMin()).isEqualTo(1);
        assertThat(securityProperties.getRefreshExpDay()).isEqualTo(1);
    }

}
