package com.ceco.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@ApiModel("分页请求对象")
public class BasePageReq implements Serializable {
    private static final long serialVersionUID = 7328071045193618467L;
    @ApiModelProperty(value = "页面大小",example = "10")
    @Min(value = 1,message = "页面大小最小为1")
    private int pageSize = 10;
    @ApiModelProperty(value = "页面号码",example = "1")
    @Min(value = 1,message = "页面大小最小为1")
    private int pageNum = 1;

}
