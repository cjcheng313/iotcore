package com.ceco.channel.app.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel("房间设备请求对象")
public class RoomDeviceAddReq {

    @ApiModelProperty("房间id")
    @NotEmpty(message = "房间id不能为空")
    private String roomId;

    @ApiModelProperty("设备序id")
    @NotEmpty(message = "设备序id不能为空")
    private List<String> deviceIdList;
}
