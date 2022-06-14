package com.ccj.configure;

import com.aliyun.mns.client.CloudAccount;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @auther Dean
 * @Date 2021/10/15.
 */
@Configuration
@Component
@ConfigurationProperties(prefix = "aliyun.iot")
@Data
public class AliyunIOTConfig {
    private String accessKeyId;  //ali id
    private String accessKeySecret;//ali secret
    private String mns;//mns 消息中间件
    private String instanceId;//IOT平台实例ID
    private String lotProductKey;//智能灯产品key
    private String queue;//消息队列
    private String endpoint; //iot实例 在的位置端点
    @Bean
    public CloudAccount cloudAccount(){
        CloudAccount account = new CloudAccount( accessKeyId, accessKeySecret, mns);
        return account;
    }
}
