package com.ccj.channel.app.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel("群组保存请求对象")
public class GroupSaveReq {


    @ApiModelProperty("群组名称")
    @NotEmpty(message = "群组名称不能为空")
    private String name;

    @ApiModelProperty("用户id")
    @NotEmpty(message = "用户id不能为空")
    private String userId;

    @ApiModelProperty("群组id")
    private String id;
}
