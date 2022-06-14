package com.ceco.channel.app.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("已连接设备")
public class ConnectedDeviceResp {


    @ApiModelProperty("房间的设备信息")
    private List<HomeResp> homeRespList;

    @ApiModelProperty("分组的设备信息")
    private List<GroupResp> groupRespList;
}
