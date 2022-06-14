package com.ccj.channel.admin.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("面板返回对象")
public class DevicePanelResp {

    @ApiModelProperty("支持的面板信息：1.彩光、2白光、3、光效、4、音乐5、节律5、膜片")
    private Integer supportPanel;

    @ApiModelProperty("面版Id")
    private String id;

    @ApiModelProperty("设备类型")
    private String deviceType;

    @ApiModelProperty("面板支持的设备型号信息")
    private List<DeviceModelResp> deviceModelRespList;

}
