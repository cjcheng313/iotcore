package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

/**
 * 登记信息类型
 * 登记信息类型 1：电话，2，姓名，3其他
 */
@ApiModel(value = "登记信息类型",description = "登记信息类型 1：电话，2，姓名，3其他")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumLiveRegisterType implements BaseEnum<Integer> {
    MOBILE(1,"电话"),
    NAME(2,"姓名"),
    OTHER(3,"其他");
    private final Integer value;
    private final String text;

    private EnumLiveRegisterType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }
    public static EnumLiveRegisterType getByValue(String value){
        EnumLiveRegisterType enums[] = EnumLiveRegisterType.values();
        for(EnumLiveRegisterType enumOne : enums){
            if(enumOne.getValue().equals(value)){
                return enumOne;
            }
        }
        return null;
    }
    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getText() {
        return text;
    }
}
