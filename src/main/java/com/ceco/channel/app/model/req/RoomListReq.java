package com.ccj.channel.app.model.req;

import com.ccj.common.utils.BasePageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("房间列表请求对象")
public class RoomListReq extends BasePageReq {

    @ApiModelProperty("homeId")
    private String homeId;

}


