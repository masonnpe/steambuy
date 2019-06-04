package com.steambuy.common.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String formPassToDBPass(String formPass,String salt) {
        return md5(formPass+salt);
    }

    public static void main(String[] args) {
        System.out.println(formPassToDBPass("ed950b3031a153cdc6fb5a51583544b2","masonnpe"));
    }
}
