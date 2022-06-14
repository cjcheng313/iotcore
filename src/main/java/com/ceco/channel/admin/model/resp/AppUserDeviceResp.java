package com.ceco.channel.admin.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户设备及位置信息")
public class AppUserDeviceResp {

    @ApiModelProperty("设备id")
    private String deviceId;

    @ApiModelProperty("设备名称")
    private String deviceName;

    @ApiModelProperty("设备序列号")
    private String serialNo;

    @ApiModelProperty("家id")
    private String homeId;

    @ApiModelProperty("家名称")
    private String homeName;

    @ApiModelProperty("房间id")
    private String roomId;

    @ApiModelProperty("房间名称")
    private String roomName;
}
