package com.ceco.channel.admin.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@ApiModel("模式保存请求对象")
@Data
public class ModelSaveReq {

    @ApiModelProperty("模式名称")
    @NotEmpty(message ="名称不能为空")
    private String name;
    @ApiModelProperty("图片地址")
    private String imgUrl;

    @ApiModelProperty("亮度值")
    @NotEmpty(message ="亮度值不能为空")
    private String brightnessValue;
    @ApiModelProperty("色温值")
    @NotEmpty(message ="色温值不能为空")

    private String colorTemperatureValue;
    @ApiModelProperty("hsv值")
    @NotEmpty(message ="hsv值不能为空")
    private String hsvValue;

}
