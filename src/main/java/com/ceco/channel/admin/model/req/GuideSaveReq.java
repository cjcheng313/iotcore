package com.ceco.channel.admin.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("引导页保存请求对象")
public class GuideSaveReq {

    @ApiModelProperty("系统类型：1:ios2:安卓3:window4:其他")
    @NotEmpty(message = "系统类型不能为空!")
    private Integer sysType;

    @ApiModelProperty("图片地址")
    @NotEmpty(message = "图片地址不能为空!")
    private String imgUrl;

    @ApiModelProperty("开始时间 ")
    private Date startDate;

    @ApiModelProperty("结束时间 ")
    private Date endDate;

}
