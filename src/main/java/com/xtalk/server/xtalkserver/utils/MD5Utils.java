package com.xtalk.server.xtalkserver.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author xin.z
 * @date 2021/1/22 8:55 下午
 */
public class MD5Utils {
    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("md5 err!");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static void main(String[] args) {
        String root = stringToMD5("root");
        System.out.println(root);
    }

    public static String hash(String text, int salt) {
        if (StringUtils.isEmpty(text)) {
            return null;
        } else {
            text = text + "|" + salt;
            return DigestUtils.sha1Hex(text);
        }
    }
}
