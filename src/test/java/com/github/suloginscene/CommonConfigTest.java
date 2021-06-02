package com.github.suloginscene;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class CommonConfigTest {

    @Test
    void create() {
        CommonConfig commonConfig = new CommonConfig();

        assertThat(commonConfig).isNotNull();
    }

}
