package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(value = "角色类型",description = "1,系统角色,2,商户角色")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumRoleType implements Serializable {
    SYSTEMROLE(1,"系统角色"),
    MERCHANTROLE(2,"商户角色");
    private final Integer value;
    private final String text;

    private EnumRoleType(Integer value, String text) {
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
