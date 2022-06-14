package com.ccj.channel.admin.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel("国家保存对象")
public class CountrySaveReq {

    @ApiModelProperty("国家名称")
    @NotEmpty(message = "国家名称不能为空!")
    private String countryName;

    @ApiModelProperty("id")
    private String id;
}
