package com.github.suloginscene.time;

import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class DateTimeFormatters {

    public static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final String DATE_REGEXP = "\\d\\d\\d\\d-\\d\\d-\\d\\d";
    public static final String DATE_MESSAGE = "날짜는 yyyy-MM-dd 형식의 숫자로 이루어져야 합니다.";

}
