package com.ccj.channel.app.model.req;

import com.ccj.common.utils.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("家庭列表请求对象")
public class HomeListReq extends BasePageReq {


    @ApiModelProperty("用户id")
    private String appUserId;
}


