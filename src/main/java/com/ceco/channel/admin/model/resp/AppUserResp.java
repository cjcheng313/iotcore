package com.ceco.channel.admin.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("pc端用户列表响应对象")
public class AppUserResp {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("来源:1:小程序2app")
    private Integer source;

    @ApiModelProperty("最后一次登录时间")
    private Date lastLoginDate;

    @ApiModelProperty("头像地址")
    private String headImg;


}
