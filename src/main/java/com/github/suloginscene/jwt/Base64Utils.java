package com.github.suloginscene.jwt;

import java.util.Base64;


class Base64Utils {

    static String encoded(String string) {
        return Base64.getEncoder()
                .encodeToString(string.getBytes());
    }

}
