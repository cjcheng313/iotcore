package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

/**
 * 交易类型枚举类型
 */
@ApiModel(value = "交易类型枚举类型",description = "1,微信，2，现金,3,银行卡")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumTransactionType implements BaseEnum<Integer> {
    WECHAT(1,"微信"),
    SUCCESS(2,"现金"),
    FAIL(3,"银行卡");
    private final Integer value;
    private final String text;

    private EnumTransactionType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }
    public static EnumTransactionType getByValue(String value){
        EnumTransactionType enums[] = EnumTransactionType.values();
        for(EnumTransactionType enumOne : enums){
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
