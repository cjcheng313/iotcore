package com.ccj.channel.app.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("设备注册")
@Data
public class DeviceRegisterReq {

    @ApiModelProperty("用户id")
    private String appUserId;

    @ApiModelProperty("设备名称")
    private String name;

    @ApiModelProperty("设备序列号")
    private String serialNo;


}
