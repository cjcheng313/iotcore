package com.ccj.channel.admin.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户登录响应请求对象")
public class UserLoginResp {

    @ApiModelProperty(value = "用户id")
    private String id;
    @ApiModelProperty(value = "用户token")
    private String userToken;
    @ApiModelProperty(value = "有效时间")
    private Integer expire;
}
