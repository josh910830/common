package com.github.suloginscene.string;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
public class QueryStringBuilder {

    private final StringBuilder sb = new StringBuilder();


    public static QueryStringBuilder create() {
        return new QueryStringBuilder();
    }


    public QueryStringBuilder add(String key, String value) {
        String prefix = (sb.length() == 0) ? "?" : "&";
        sb.append(prefix).append(key).append("=").append(value);
        return this;
    }

    public String build() {
        return sb.toString();
    }

}
