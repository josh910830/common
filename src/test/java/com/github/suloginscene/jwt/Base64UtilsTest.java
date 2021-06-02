package com.github.suloginscene.jwt;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class Base64UtilsTest {

    @Test
    void test() {
        String text = "text";

        String encoded = Base64Utils.encoded(text);

        assertThat(encoded).isNotEqualTo(text);
    }

}