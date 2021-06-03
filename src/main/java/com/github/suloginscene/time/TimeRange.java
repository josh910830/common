package com.github.suloginscene.time;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static com.github.suloginscene.time.DateTimeFormatters.DATE_TIME;
import static lombok.AccessLevel.PRIVATE;


@Data
@RequiredArgsConstructor(access = PRIVATE)
public class TimeRange {

    private final LocalDateTime begin;
    private final LocalDateTime end;


    public static TimeRange of(LocalDateTime begin, LocalDateTime end) {
        return new TimeRange(begin, end);
    }


    public String beginString() {
        return begin.format(DATE_TIME);
    }

    public String endString() {
        return end.format(DATE_TIME);
    }


    public boolean contains(LocalDateTime localDateTime) {
        boolean closed = localDateTime.isEqual(begin) || localDateTime.isAfter(begin);
        boolean open = localDateTime.isBefore(end);
        return closed && open;
    }

}
