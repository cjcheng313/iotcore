package com.ceco.channel.admin.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel("场景保存请求对象")
@Data
public class SceneResp extends ModelResp{

    @ApiModelProperty("显示区域:1白光灯2彩光灯3流光灯")
    private Integer showArea;

}
