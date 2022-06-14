package com.ceco.common.properties;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "auth.user")
public class UserProperties {

    private String tokenHeader;
    private String corpHeader;
    private int expire;
    private String hexPriKey;
    private String hexPubKey;

    private static UserProperties instance;


    public UserProperties() {
        instance = this;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public void setCorpHeader(String corpHeader) {
        this.corpHeader = corpHeader;
    }

    public void setHexPriKey(String hexPriKey) {
        this.hexPriKey = hexPriKey;
    }

    public void setHexPubKey(String hexPubKey) {
        this.hexPubKey = hexPubKey;
    }

    public static String getTokenHeader() {
        return instance.tokenHeader;
    }

    public static int getExpire() {
        return instance.expire;
    }

    public static String getCorpHeader() {
        return instance.corpHeader;
    }

    public static String getHexPriKey() {
        return instance.hexPriKey;
    }

    public static byte[] getPriKeyBytes() throws DecoderException {
        return Hex.decodeHex(instance.hexPriKey);
    }

    public static String getHexPubKey() {
        return instance.hexPubKey;
    }

    public static byte[] getPubKeyBytes() throws DecoderException {
        return Hex.decodeHex(instance.hexPubKey);
    }
}
