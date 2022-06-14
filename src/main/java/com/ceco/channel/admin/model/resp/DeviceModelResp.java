package com.ceco.channel.admin.model.resp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@ApiModel("设备类型响应请求对象")
@Data
public class DeviceModelResp {

    @ApiModelProperty("设备型号名称")
    private String modelName;


    @ApiModelProperty("设备类型")
    private String deviceType;


    @ApiModelProperty("设备图片地址")
    private String imgUrl;


    @ApiModelProperty("设备图标")
    private String imgIcon;

    @ApiModelProperty("创建人")
    private String createName;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @JsonIgnore
    @ApiModelProperty("支持的硬件版本id")
    private String versionId;

    @JsonIgnore
    @ApiModelProperty("支持的面板id")
    private String panelId;
}
