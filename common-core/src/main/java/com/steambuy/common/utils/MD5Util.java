package com.steambuy.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    private static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String formPassToDBPass(String formPass, String saltDB) {
        return md5(formPass+saltDB);
    }

}
