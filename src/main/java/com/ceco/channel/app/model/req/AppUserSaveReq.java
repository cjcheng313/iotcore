package com.ceco.channel.app.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel("用户信息编辑请求对象")
public class AppUserSaveReq {

    @ApiModelProperty("用户id")
    @NotEmpty(message = "id不能为空")
    private String id;

    @ApiModelProperty("昵称")
    @NotEmpty(message = "昵称不能为空")
    private String nickName;

    @ApiModelProperty("头像地址")
    private String headImg;
}
