package com.github.suloginscene.jwt;

import lombok.NoArgsConstructor;

import java.util.Base64;

import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
class Base64Utils {

    static String encoded(String string) {
        return Base64.getEncoder()
                .encodeToString(string.getBytes());
    }

}
