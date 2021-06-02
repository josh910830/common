package com.github.suloginscene.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DisplayName("프로퍼티 - 앱")
class AppPropertiesTest {

    AppProperties appProperties;


    @BeforeEach
    void setup() {
        appProperties = new AppProperties();
    }


    @Test
    @DisplayName("기본값")
    void defaultValues() {
        assertThat(appProperties.getAddress()).isNotNull();
    }

    @Test
    @DisplayName("설정")
    void setValues() {
        appProperties.setAddress("https://app.com");

        assertThat(appProperties.getAddress()).isEqualTo("https://app.com");
    }

}
