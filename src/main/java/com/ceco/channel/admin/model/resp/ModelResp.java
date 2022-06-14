package com.ceco.channel.admin.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@ApiModel("模式保存请求对象")
@Data
public class ModelResp {

    @ApiModelProperty("模式名称")
    private String name;
    @ApiModelProperty("图片地址")
    private String imgUrl;
    @ApiModelProperty("亮度值")
    private String brightnessValue;
    @ApiModelProperty("色温值")
    private String colorTemperatureValue;
    @ApiModelProperty("hsv值")
    private String hsvValue;
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("创建人")
    private Date createName;

}
