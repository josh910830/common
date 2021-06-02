package com.github.suloginscene.time;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("시간 기간")
class TimeRangeTest {

    @Test
    @DisplayName("테스트")
    void test() {
        LocalDateTime before = LocalDateTime.now();
        LocalDateTime begin = LocalDateTime.now();
        LocalDateTime center = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime after = LocalDateTime.now();

        TimeRange timeRange = TimeRange.of(begin, end);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        assertThat(timeRange.beginString()).isEqualTo(begin.format(formatter));
        assertThat(timeRange.endString()).isEqualTo(end.format(formatter));

        assertThat(timeRange.contains(before)).isFalse();
        assertThat(timeRange.contains(begin)).isTrue();
        assertThat(timeRange.contains(center)).isTrue();
        assertThat(timeRange.contains(end)).isFalse();
        assertThat(timeRange.contains(after)).isFalse();
    }

}
