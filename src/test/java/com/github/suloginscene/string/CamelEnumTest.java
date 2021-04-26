package com.github.suloginscene.string;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("캐멀케이스 이늄")
class CamelEnumTest {

    @Test
    @DisplayName("1단어")
    void toCamel_1word() {
        TestEnum capitalUnderscore = TestEnum.TEST;
        String camelCase = capitalUnderscore.toCamel();
        assertThat(camelCase).isEqualTo("test");
    }

    @Test
    @DisplayName("2단어")
    void toCamel_2word() {
        TestEnum capitalUnderscore = TestEnum.TEST_ENUM;
        String camelCase = capitalUnderscore.toCamel();
        assertThat(camelCase).isEqualTo("testEnum");
    }

    @Test
    @DisplayName("3단어")
    void toCamel_3word() {
        TestEnum capitalUnderscore = TestEnum.TEST_ENUM_NAME;
        String camelCase = capitalUnderscore.toCamel();
        assertThat(camelCase).isEqualTo("testEnumName");
    }


    enum TestEnum implements CamelEnum {
        TEST, TEST_ENUM, TEST_ENUM_NAME
    }

}
