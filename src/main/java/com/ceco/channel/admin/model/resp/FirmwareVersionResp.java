package com.ceco.channel.admin.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@ApiModel("设备类型保存请求对象")
@Data
public class FirmwareVersionResp {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("固件名称")
    private String name;

    @ApiModelProperty("固件key")
    private String firmwareKey;

    @ApiModelProperty("固件版本")
    private String version;

    @ApiModelProperty("设备类型")
    private String deviceType;

    @ApiModelProperty("下载地址")
    private String downloadUrl;

    @ApiModelProperty("支持的的设备类型对象")
    private List<DeviceModelResp> deviceModelRespList;

}
