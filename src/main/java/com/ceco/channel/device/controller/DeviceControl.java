// This file is auto-generated, don't edit it. Thanks.
package com.ccj.channel.device.controller;

import com.aliyun.iot20180120.models.*;
import com.aliyun.teaopenapi.models.*;

public class Deviccjntrol {

    /**
     * 使用AK&SK初始化账号Client
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.iot20180120.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "iot.cn-shanghai.aliyuncs.com";
        return new com.aliyun.iot20180120.Client(config);
    }

    public static void main(String[] args_) throws Exception {
        java.util.List<String> args = java.util.Arrays.asList(args_);
        com.aliyun.iot20180120.Client client = Deviccjntrol.createClient("LTAI5tH93jjs39zfKYmcZJKo", "30l3anWTOS4PAuvtOSDtJrZgw6jvtz");
        SetDevicePropertyRequest setDevicePropertyRequest = new SetDevicePropertyRequest()
                .setIotInstanceId("iot-06z00d7vxpjd8il")
                .setProductKey("ge6fUo77lY6")
                .setDeviceName("lot_json_0001")
                .setItems("{\"switch_led\":0,\"bright_value\":321}");
        // 复制代码运行请自行打印 API 的返回值
        client.setDeviceProperty(setDevicePropertyRequest);
    }
}
