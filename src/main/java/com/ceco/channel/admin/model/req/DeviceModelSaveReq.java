package com.ccj.channel.admin.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@ApiModel("设备类型保存请求对象")
@Data
public class DeviceModelSaveReq {

    @ApiModelProperty("设备型号名称")
    @NotEmpty(message = "设备型号不能为空")
    private String modelName;


    @ApiModelProperty("设备类型")
    @NotEmpty(message = "设备类型不能为空")
    private String deviceType;


    @ApiModelProperty("设备图片地址")
    @NotEmpty(message = "设备图片不能为空")
    private String imgUrl;


    @ApiModelProperty("设备图标")
    @NotEmpty(message = "设备图标不能为空")
    private String imgIcon;
}
