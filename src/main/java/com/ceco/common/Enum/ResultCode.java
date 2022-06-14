package com.ccj.common.Enum;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

/**
 * 响应码枚举，参考HTTP状态码的语义
 *
 * @author zy
 */
@ApiModel(value = "响应码枚举",description = "参考HTTP状态码的语义")
@Getter
public enum ResultCode {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 操作失败
     */
    ERROR(400, "操作失败"),
    /**
     * 未经过身份认证
     */
    AUTH_ERROR(401, "未经过身份认证"),
    /**
     * token参数不存在,无访问权限
     */
    NO_TOKEN(40101, "token参数不存在,无访问权限"),
    /**
     * token验证失败,无访问权限
     */
    CHECK_TOKEN(40102, "token验证失败,无访问权限"),
    /**
     * accessKeyId参数不存在
     */
    NO_SECRET_ID(40151, "accessKeyId参数不存在"),
    /**
     * sign签名参数不存在
     */
    NO_SECRET_SIGN(40152, "sign签名参数不存在"),
    /**
     * 请求校验失败
     */
    CHECK_SECRET(40153, "请求校验失败"),
    /**
     * 资源不存在
     */
    NOT_FOUND(404, "资源不存在"),
    /**
     * 服务器异常,请稍后再试
     */
    SYSTEM_ERROR(500, "服务器异常,请稍后再试"),
    /**
     * 用户信息解析异常,请稍后再试
     */
    USERPRINCIPAL_RESOLVER_ERROR(50001, "用户信息解析异常,请稍后再试"),
    /**
     * RPC或其他项目通信调用异常，外部服务异常
     */
    RPC_ERROR(503, "外部服务异常"),
    RECORD_NOEXIST(4001, "记录不存在"),
    ;


    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}

