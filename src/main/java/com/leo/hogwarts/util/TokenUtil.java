package com.leo.hogwarts.util;

import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

/**
 * @ClassName TokenUtil
 * @Description
 * @Author Leo
 * @Date 2020/3/31 10:42
 */

public class TokenUtil {

    public static String getToken(String username) throws Exception {
        String auth = System.currentTimeMillis() + UUID.randomUUID().toString() + username;
        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] bytes = md.digest(auth.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            hexString.append(Integer.toHexString(255 & bytes[i]));
        }
        return hexString.toString();
    }

    public static String randomSalt() {
        StringBuilder val = new StringBuilder();
        Random random = new Random();
        int max = 8;

        for(int i = 0; i < max; ++i) {
            val.append((char)(random.nextInt(93) + 33));
        }

        return val.toString();
    }


    public static void main(String[] args) throws Exception {
        String salt = randomSalt();
        System.out.println(salt);
        System.out.println(DigestUtils.md5Hex("2"+salt));

    }
}
