package com.ccj.channel.admin.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户登录请求对象",description = "没有验证码可以不填验证码")
public class UserLoginReq {

    @ApiModelProperty(value = "登录请求账号",required = true)
    private String account;

    @ApiModelProperty( value ="登录请求密码",required = true)
    private String password;



}
