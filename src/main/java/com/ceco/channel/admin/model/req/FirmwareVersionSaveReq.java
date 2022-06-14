package com.ccj.channel.admin.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@ApiModel("设备类型保存请求对象")
@Data
    public class FirmwareVersionSaveReq {


    @ApiModelProperty("固件名称")
    @NotEmpty(message = "固件名称不能为空！")
    private String name;

    @ApiModelProperty("固件key")
    @NotEmpty(message = "固件标识不能为空！")
    private String firmwareKey;

    @ApiModelProperty("固件版本")
    @NotEmpty(message = "固件版本不能为空！")
    private String version;

    @ApiModelProperty("设备类型")
    @NotEmpty(message = "设备类型不能为空！")
    private String deviceType;

    @ApiModelProperty("下载地址")
    @NotEmpty(message = "下载地址不能为空！")
    private String downloadUrl;

    @ApiModelProperty("支持设备类型")
    @NotEmpty(message = "支持设备类型不能为空！")
    private List<String> deviceModel;

}
