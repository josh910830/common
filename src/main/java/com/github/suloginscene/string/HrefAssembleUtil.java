package com.github.suloginscene.string;

import com.github.suloginscene.exception.InternalException;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;


@NoArgsConstructor(access = PRIVATE)
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
