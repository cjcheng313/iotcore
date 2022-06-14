package com.ccj.common.utils.encrypt;

import java.security.MessageDigest;

public class MD5Encode {

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String getMD5Str(String str) {
        String resStr = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            resStr = byteArrayToHexString(md.digest(str.getBytes()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resStr;
    }

    /**
     *      * 转换字节数组为16进制字串
     *      * 
     *      * @param b
     *      *            字节数组
     *      * @return 16进制字串
     *     
     */

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resSb.append(byteToHexString(b[i]));
        }
        return resSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
