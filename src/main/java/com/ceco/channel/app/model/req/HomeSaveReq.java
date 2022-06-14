package com.ccj.channel.app.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("家庭保存请求对象")
public class HomeSaveReq {


    @ApiModelProperty("家庭名称")
    @NotEmpty(message = "家庭名称不能为空")
    private String name;

    @ApiModelProperty("用户id")
    @NotEmpty(message = "用户id不能为空")
    private String userId;

    @ApiModelProperty("家庭id")
    private String id;
}
