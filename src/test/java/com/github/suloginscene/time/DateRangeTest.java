package com.github.suloginscene.time;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("날짜 기간")
class DateRangeTest {

    @Test
    @DisplayName("테스트")
    void test() {
        DateRange todayRange = DateRange.today();

        LocalDate todayDate = LocalDate.now();
        LocalDate tomorrowDate = todayDate.plusDays(1L);

        assertThat(todayRange.getBegin()).isEqualTo(todayDate);
        assertThat(todayRange.getEnd()).isEqualTo(tomorrowDate);
        assertThat(todayRange.days()).isEqualTo(1);

        DateTimeFormatter formatter = ofPattern("yyyy-MM-dd");
        assertThat(todayRange.beginString()).isEqualTo(todayDate.format(formatter));
        assertThat(todayRange.inclusiveEndString()).isEqualTo(todayDate.format(formatter));
        assertThat(todayRange.exclusiveEndString()).isEqualTo(tomorrowDate.format(formatter));

        TimeRange timeRange = todayRange.toTimeRange();
        assertThat(timeRange.getBegin()).isEqualTo(todayDate.atStartOfDay());
        assertThat(timeRange.getEnd()).isEqualTo(tomorrowDate.atStartOfDay().minusNanos(1L));
    }

}
