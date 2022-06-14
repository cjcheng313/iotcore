package com.ceco.channel.app.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@ApiModel("用户注册")
@Data
public class AppUserRegisterReq {

    @ApiModelProperty("用户账号")
    @NotEmpty(message = "用户账号不能为空")
    private String account;

    @ApiModelProperty("账号来源:1国内、2国外")
    private Integer source;

    @ApiModelProperty("验证码")
    @NotEmpty(message = "验证码不能为空")
    private String verifyCode;

    @ApiModelProperty("密码")
    @NotEmpty(message = "密码不能为空")
    private String password;
}
