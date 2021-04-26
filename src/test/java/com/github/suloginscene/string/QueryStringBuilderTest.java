package com.github.suloginscene.string;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("쿼리스트링 빌더")
class QueryStringBuilderTest {

    @Test
    @DisplayName("1쌍")
    void build1Pair() {
        String queryString = QueryStringBuilder.create()
                .add("k", "v")
                .build();
        assertThat(queryString).isEqualTo("?k=v");
    }

    @Test
    @DisplayName("2쌍")
    void build2Pair() {
        String queryString = QueryStringBuilder.create()
                .add("k1", "v1")
                .add("k2", "v2")
                .build();
        assertThat(queryString).isEqualTo("?k1=v1&k2=v2");
    }

    @Test
    @DisplayName("3쌍")
    void build3Pair() {
        String queryString = QueryStringBuilder.create()
                .add("k1", "v1")
                .add("k2", "v2")
                .add("k3", "v3")
                .build();
        assertThat(queryString).isEqualTo("?k1=v1&k2=v2&k3=v3");
    }

}
