package com.ceco.common.utils.response;

import com.ceco.common.Enum.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.io.Serializable;

/**
 * 统一API响应结果封装
 *
 * @author zy
 */
@ApiModel("统一API响应结果封装")
@Getter
public class ResultResponse<T> implements Serializable {
    /**
     * 状态码
     */
    @ApiModelProperty(value = "响应状态码", required = true)
    private final int code;
    /**
     * 响应信息，用来说明响应情况
     */
    @ApiModelProperty(value = "响应信息", required = true)
    private final String msg;
    /**
     * 响应的具体数据
     */
    @ApiModelProperty(value = "响应数据", required = false)
    private T data;

    public ResultResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultResponse(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultResponse(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public ResultResponse(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }


    /**
     * 返回成功消息
     *
     * @param data 数据对象
     * @return 成功消息
     */
    public static <T> ResultResponse<T> success(T data) {
        return new ResultResponse<>(ResultCode.SUCCESS, data);
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static <T> ResultResponse<T> success() {
        return new ResultResponse<>(ResultCode.SUCCESS);
    }

    /**
     * 返回错误消息
     *
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> ResultResponse<T> error(T data) {
        return new ResultResponse<>(ResultCode.SYSTEM_ERROR, data);
    }

    /**
     * 返回错误消息
     *
     * @return 警告消息
     */
    public static <T> ResultResponse<T> error() {
        return new ResultResponse<>(ResultCode.SYSTEM_ERROR);
    }

    /**
     * 操作是否成功
     *
     * @return 警告消息
     */
    public static  ResultResponse isSuccess(boolean flag) {
        if (flag) {
            return new ResultResponse<>(ResultCode.SUCCESS,true);
        }
        return new ResultResponse<>(ResultCode.ERROR,false);
    }

    /**
     * 自定义响应码，响应信息，相应数据
     * @param <T>
     * @return
     */
    public static <T> ResultResponse<T> response(ResultCode resultCode,T data) {
        return new ResultResponse<>(resultCode,data);
    }
    public static <T> ResultResponse<T> response(int code,String msg ,T data) {
        return new ResultResponse<>(code,msg,data);
    }
}
