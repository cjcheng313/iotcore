package com.ceco.channel.admin.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel("国家响应对象")
@Data
public class CountryResp {

    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("国家名称")
    private String countryName;
    @ApiModelProperty("创建人")
    private String createName;
    @ApiModelProperty("创建时间")
    private Date   createTime;
}
