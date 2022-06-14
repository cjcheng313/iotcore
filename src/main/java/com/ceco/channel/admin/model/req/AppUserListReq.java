package com.ccj.channel.admin.model.req;

import com.ccj.common.utils.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("app用户列表请求对象")
public class AppUserListReq extends BasePageReq {

    @ApiModelProperty("昵称")
    private String nickName;
}
