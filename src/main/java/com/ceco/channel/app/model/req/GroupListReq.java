package com.ccj.channel.app.model.req;

import com.ccj.common.utils.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("群组列表请求对象")
public class GroupListReq extends BasePageReq {


    @ApiModelProperty("用户id")
    private String appUserId;
}


