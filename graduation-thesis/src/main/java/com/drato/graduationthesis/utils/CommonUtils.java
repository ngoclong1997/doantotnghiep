package com.drato.graduationthesis.utils;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
    public static byte[] stringToBytes(String s) {
        return s.getBytes(StandardCharsets.UTF_8);
    }

    public static String bytesToString(byte[] bs) {
        return new String(bs);
    }

    public static String getMd5Hash(String s) throws NoSuchAlgorithmException {
        byte[] bs = stringToBytes(s);
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(bs);
        return new String(Hex.encodeHex(digest));
    }

    public static String getFileExtension(String s) {
        int i = s.length()-1;
        StringBuilder result = new StringBuilder();
        while (s.charAt(i) != '.' && i > 0) {
            result.append(s.charAt(i));
            i--;
        }
        result.append('.');
        return result.reverse().toString();
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
