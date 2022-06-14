package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

/**
 *提现状态
 */
@ApiModel(value = "提现状态",description = "0,拒绝,1,通过")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumWithdrawType implements BaseEnum<Integer> {
    PROCESS(0,"拒绝"),
    SUCCESS(1,"通过");
    private final Integer value;
    private final String text;

    private EnumWithdrawType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }
    public static EnumWithdrawType getByValue(String value){
        EnumWithdrawType enums[] = EnumWithdrawType.values();
        for(EnumWithdrawType enumOne : enums){
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
