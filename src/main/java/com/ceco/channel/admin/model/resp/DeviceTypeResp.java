package com.ccj.channel.admin.model.resp;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("已有设备类型返回对象")
public class DeviceTypeResp {


    private String deviceType;
}
