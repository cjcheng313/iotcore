package com.ccj.common.utils.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.io.Serializable;

@ApiModel(description = "返回结果",value = "返回结果模型")
public class ResponseModel<T> implements Serializable {
    private static final long serialVersionUID = -93744850105709929L;

    public static final Integer SUCCESS_CODE = 200;
    public static final Integer FAIL_CODE = 500;
    public static final ResponseModel SUCCESS = new ResponseModel(SUCCESS_CODE);
    public static final ResponseModel FAIL = new ResponseModel(FAIL_CODE, "fail");
    @ApiModelProperty("返回编码")
    @Getter
    private Integer code;
    @ApiModelProperty("描述性原因")
    @Getter
    private String msg;
    @ApiModelProperty("业务数据")
    @Getter
    private T data;


    private ResponseModel() {
    }

    public ResponseModel(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ResponseModel(Integer code) {
        this.code = code;
        this.msg = "ok";
    }

    private ResponseModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseModel success(Integer code, String msg,Object data) {
        return new ResponseModel(code,msg,data);
    }
    public static ResponseModel success() {
        return SUCCESS;
    }

    public static ResponseModel success(Object data) {
        return new ResponseModel(SUCCESS_CODE).setData(data);
    }
    public static ResponseModel success(String msg) {
        return new ResponseModel(SUCCESS_CODE).setMsg(msg);
    }
    public static ResponseModel fail() {
        return FAIL;
    }

    public static ResponseModel fail(String msg) {
        return new ResponseModel(FAIL_CODE).setMsg(msg);
    }

    public static ResponseModel fail(Integer code, String msg) {
        return new ResponseModel(code).setMsg(msg);
    }

    private ResponseModel setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    private ResponseModel setData(T data) {
        this.data = data;
        return this;
    }



}
