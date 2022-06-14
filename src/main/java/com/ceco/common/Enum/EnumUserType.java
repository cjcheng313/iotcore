package com.ceco.common.Enum;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "用户类型",description = "用户类型1系统用户,2商户用户,3代理商用户")
public enum EnumUserType {
    SYSTEMUSER(1,"系统用户"),
    MERCHANTUSER(2,"商户用户"),
    AGEENTUSER(3,"代理商用户");

    private final Integer value;
    private final String text;

    private EnumUserType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * 获取value
     */
    public Integer getValue() {
        return this.value;
    }

    /**
     * 获取Text
     */
    public String getText() {
        return this.text;
    }
}
