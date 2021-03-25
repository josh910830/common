package com.github.suloginscene.jjwthelper;

import java.util.Base64;


public class Base64Utils {

    public static String encoded(String string) {
        return Base64.getEncoder()
                .encodeToString(string.getBytes());
    }

}
