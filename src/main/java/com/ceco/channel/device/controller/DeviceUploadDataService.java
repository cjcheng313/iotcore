package com.ccj.channel.device.controller;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.model.Message;
import com.ccj.configure.AliyunIOTConfig;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Dean on 2021/10/15.
 * 设备上报的指令数据
 */
@Slf4j
@Component
public class DeviceUploadDataService {
    @Autowired
    private CloudAccount cloudAccount;

    @Autowired
    private AliyunIOTConfig aliyunIOTConfig;
    @PostConstruct
    public void init(){
        MNSClient client = cloudAccount.getMNSClient();
        CloudQueue queue = client.getQueueRef(aliyunIOTConfig.getQueue()); //请输入物联网平台自动创建的队列名称。

        while (true) {
            // 获取消息。
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message popMsg = queue.popMessage(5); //长轮询等待时间为10秒。
            if (popMsg != null) {
                BASE64Decoder decoder = new BASE64Decoder();
                String initialMsg=popMsg.getMessageBodyAsRawString();
                try {
                    String msgBody=new String(decoder.decodeBuffer(initialMsg), "UTF-8");
                    Gson gson = new Gson();
                    Map msgBodyMap =gson.fromJson(msgBody,Map.class);
                    String base64EncoderPayload=(String)msgBodyMap.get("payload");
                    String base64DecoderPayload=new String( decoder.decodeBuffer(base64EncoderPayload),"UTF-8");
                    System.out.println(base64DecoderPayload); //打印payload数据

                } catch (IOException e) {
                    throw  new RuntimeException("MNS解码失败");
                }
                //    System.out.println("PopMessage Body: "+ popMsg.getMessageBodyAsRawString()); //获取原始消息。
                queue.deleteMessage(popMsg.getReceiptHandle()); //从队列中删除消息。
            } else {
//                System.out.println("Continuing");
            }
        }
    }
}
