package com.ceco.channel.app.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("设备信息返回对象")
@Data
public class DeviceResp {

    private String id;

    @ApiModelProperty("设备序列号")
    private String serialNo;

    @ApiModelProperty("设备名称")
    private String name;

    @ApiModelProperty("设备所属组")
    private String groupId;

    @ApiModelProperty("设备所属房间")
    private String roomId;

}
