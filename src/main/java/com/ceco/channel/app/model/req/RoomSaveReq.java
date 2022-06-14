package com.ccj.channel.app.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel("房间保存请求对象")
public class RoomSaveReq {


    @ApiModelProperty("房间名称")
    @NotEmpty(message = "房间名称不能为空")
    private String name;

    @ApiModelProperty("家庭id")
    @NotEmpty(message = "家庭id不能为空")
    private String homeId;

    @ApiModelProperty("房间id")
    private String id;

}
