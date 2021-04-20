package com.github.suloginscene.string;


public class HrefAssembleUtil {

    private static String address;


    public static void setAddress(String appAddress) {
        address = appAddress;
    }

    public static String href(String path) {
        return address + path;
    }

}
