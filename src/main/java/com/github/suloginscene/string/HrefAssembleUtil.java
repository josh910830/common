package com.github.suloginscene.string;

import com.github.suloginscene.exception.InternalException;


public class HrefAssembleUtil {

    private static String address;


    public static void setAddress(String appAddress) {
        address = appAddress;
    }

    public static String href(String path) {
        if (address == null) throw new InternalException("address is unset");
        return address + path;
    }

}
