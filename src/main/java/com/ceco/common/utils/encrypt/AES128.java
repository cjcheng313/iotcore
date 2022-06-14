package com.ccj.common.utils.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

//https://www.oschina.net/code/snippet_242957_9931
public class AES128 {
	private final static String PASSWORD = "!!king?DeE|12345";
	
	/**
	 * 加密 
	 * @Description TODO  	V1.0
	 * @Author		wenqiang_tang		2017年3月24日 上午10:08:57
	 * @param @param content 加密内容
	 * @param @param password 密码
	 * @param @return
	 * @param @throws Exception 参数
	 * @return String 返回类型 
	 * @throws
	 */
    public static String encrypt(String content) throws Exception {
        byte[] raw = PASSWORD.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }
    
	/**
	 * 加密 
	 * @Description TODO  	V1.0
	 * @Author		wenqiang_tang		2017年3月24日 上午10:08:57
	 * @param @param content 加密内容
	 * @param @param password 密码
	 * @param @return
	 * @param @throws Exception 参数
	 * @return String 返回类型 
	 * @throws
	 */
    public static String encrypt(String content, String password) throws Exception {
    	password = password + "" ;
        // 判断Key是否为16位
        if (password.length() != 16) {
            System.out.print("Key长度不是16位");
            return "";
        }
        byte[] raw = password.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }
 
    /**
     * 解密
     * @Description TODO  	V1.0
     * @Author		wenqiang_tang		2017年3月24日 上午10:09:18
     * @param @param content 解密内容
     * @param @param password 密码
     * @param @return
     * @param @throws Exception 参数
     * @return String 返回类型 
     * @throws
     */
    public static String decrypt(String content, String password) throws Exception {
        try {
        	password = password+"" ;
            // 判断Key是否为16位
            if (password.length() != 16) {
                System.out.print("Key长度不是16位");
                return "";
            }
            byte[] raw = password.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(content);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,"utf-8");
            return originalString;
        } catch (Exception ex) {
            return "";
        }
    }
    
    /**
     * 解密
     * @Description TODO  	V1.0
     * @Author		wenqiang_tang		2017年3月24日 上午10:09:18
     * @param @param content 解密内容
     * @param @param password 密码
     * @param @return
     * @param @throws Exception 参数
     * @return String 返回类型 
     * @throws
     */
    public static String decrypt(String content) throws Exception {
        try {
            byte[] raw = PASSWORD.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(content);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original,"utf-8");
            return originalString;
        } catch (Exception ex) {
            return "";
        }
    }
 
    public static void main(String[] args) throws Exception {
        /*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         */
        //String cKey = "!!king?DeE|12345";
        // 需要加密的字串
        //String cSrc = "3064003862";
        //System.out.println(cSrc);
        // 加密
       // String enString = AES128.Encrypt(cSrc, cKey);
        //System.out.println("加密后的字串是：" + enString);

        // 解密
    	/*String enString = "hWa3Mj4nW+8FNWsznzXSm9EFocAxFD8bs7RWSSVIoq5/Vwdop+aXVsInsLiXXjEuih9+u6ZSskTMiO0dh/KsqEPypievV9+TFsjYzDnzEWp0QtRQBu0lgUQCowCwlbPaNJsSgIHbMb1MeUrbDenRc0um+vEEjIggvfmeqC7ECMsNx7y0kflMVem6gB55VSyp0tm0dKOqDUp/QHooYyenSF7MiTTtOue7EslIY0WP2/PI6GL7sjbDhkUWPZUh19tasOS+dZqJmcQnJ8vPR5VqIg\u003d\u003d";
    	System.out.println("加密后后的字串是：" + enString);
    	String DeString = AES128.decrypt(enString, "!!king?DeE|12345");
        System.out.println("解密后的字串是：" + DeString);*/

    	//String requestUrl = "http://127.0.0.1:8080/bill-portalweb/portal/orcmorder/get";

    }
}

