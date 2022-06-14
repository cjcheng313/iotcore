package com.ccj.channel.admin.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("pc端app用户详情响应对象")
public class AppUserDetailResp {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("账户")
    private String account;

    @ApiModelProperty("昵称")
    private String nickName;


    private List<AppUserDeviceResp> appUserDeviceRespList = new ArrayList<>();
}
