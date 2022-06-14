package com.ceco.channel.app.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@ApiModel("房间保存请求对象")
@Data
public class HomeResp {

    @ApiModelProperty("家庭名称")
    private Integer name;

    @ApiModelProperty("房间信息")
    private List<RoomResp> roomRespList = new ArrayList<>();

    @ApiModelProperty("家庭id")
    private String id;

}
