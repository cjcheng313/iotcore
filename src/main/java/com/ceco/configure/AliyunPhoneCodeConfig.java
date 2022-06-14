package com.ceco.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
@Component
@ConfigurationProperties(prefix = "aliyun.sms")
public class AliyunPhoneCodeConfig {
    private String defaultConnectTimeoutKey;
    private String defaultConnectTimeoutValue;
    private String defaultReadTimeoutKey;
    private String defaultReadTimeoutValue;
    private String product;
    private String domain;
    private String accessKeyId;
    private String accessKeySecret;
    private String templateCode;
    private String signName;

}
