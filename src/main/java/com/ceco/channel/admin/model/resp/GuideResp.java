package com.ccj.channel.admin.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("引导列表响应对象")
public class GuideResp {

    @ApiModelProperty("创建人")
    private String createName;
    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("系统类型：1:ios2:安卓3:window4:其他")
    @NotEmpty(message = "系统类型不能为空!")
    private Integer sysType;

    @ApiModelProperty("图片地址")
    @NotEmpty(message = "图片地址不能为空!")
    private String imgUrl;

    @ApiModelProperty("id")
    private String id;
}
