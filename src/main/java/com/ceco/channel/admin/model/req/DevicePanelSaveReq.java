package com.ccj.channel.admin.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("面板保存请求对象")
public class DevicePanelSaveReq {

    @ApiModelProperty("支持的面板信息：1.彩光、2白光、3、光效、4、音乐5、节律5、膜片")
    private List<Integer> supportPanelList;

    @ApiModelProperty("设备类型")
    private String deviceType;

    @ApiModelProperty("设备型号Id")
    private String deviceModeId;
}
