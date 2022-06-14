package com.ccj.channel.app.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("房间返回对象")
public class RoomResp {

    @ApiModelProperty("房间id")
    private String id;

    @ApiModelProperty("房间名称")
    private String name;

    @ApiModelProperty("家庭id")
    private String homeId;

    @ApiModelProperty("家庭名")
    private String homeName;

    @ApiModelProperty("房间设备信息")
    private List<DeviceResp> deviceRespList;
}
