package com.ccj.channel.app.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@ApiModel("群组保存请求对象")
@Data
public class GroupResp {

    @ApiModelProperty("群组名称")
    private Integer name;
    
    @ApiModelProperty("群组id")
    private String id;

    @ApiModelProperty("设备信息")
    private List<DeviceResp> deviceRespList =new ArrayList<>();

}
