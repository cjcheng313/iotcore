package com.ceco.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

/**
 * 财务枚举类型
 */
@ApiModel("财务枚举类型")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumFinanceType implements BaseEnum<Integer> {
    INCOME(1,"收入"),
    EXPEND(2,"支出");
    private final Integer value;
    private final String text;

    private EnumFinanceType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }
    public static EnumFinanceType getByValue(String value){
        EnumFinanceType enums[] = EnumFinanceType.values();
        for(EnumFinanceType enumOne : enums){
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
