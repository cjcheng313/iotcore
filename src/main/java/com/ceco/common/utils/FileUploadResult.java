package com.ccj.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("文件上传返回结果对象")
public class FileUploadResult implements Serializable {

    private static final long serialVersionUID = 8498670596266864630L;
    //文件
    @ApiModelProperty("文件id")
    private String id;
    // 文件名称
    @ApiModelProperty("文件名称")
    private String name;
    // 文件上传url
    @ApiModelProperty("文件上传url")
    private String url;
    // 状态有：uploading done error removed
    @ApiModelProperty("文件上传状态")
    private String status;
    // 服务端响应内容，如：'{"status": "success"}'
    @ApiModelProperty("服务端响应内容")
    private Object response;
    //文件大小
    @ApiModelProperty("文件大小")
    private Long size;
    //时间长度
    @ApiModelProperty("时间长度")
    private Long duration;

    //封面
    @ApiModelProperty("封面")
    private String coverUrl;

}
