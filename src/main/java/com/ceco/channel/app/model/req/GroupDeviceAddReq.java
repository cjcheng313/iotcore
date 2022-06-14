package com.ceco.channel.app.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel("群组设备请求对象")
public class GroupDeviceAddReq {

    @ApiModelProperty("群组id")
    @NotEmpty(message = "群组id不能为空")
    private String groupId;

    @ApiModelProperty("设备序列号列表")
    @NotEmpty(message = "设备序列号不能为空")
    private List<String> deviceIdList;
}
