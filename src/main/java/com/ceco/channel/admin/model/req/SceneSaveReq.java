package com.ccj.channel.admin.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

@ApiModel("场景保存请求对象")
@Data
public class SceneSaveReq extends ModelSaveReq{

    @ApiModelProperty("显示区域:1白光灯2彩光灯3流光灯")
    @NotNull(message = "显示区域不能为空")
    private Integer showArea;

}
