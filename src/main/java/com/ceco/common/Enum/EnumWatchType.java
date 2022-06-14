package com.ceco.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

/**
 *观看方式
 */
@ApiModel(value = "观看方式",description = "1,微信,2,pc")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumWatchType implements BaseEnum<Integer> {
    WX(1,"微信"),
    PC(2,"pc");
    private final Integer value;
    private final String text;

    private EnumWatchType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }
    public static EnumWatchType getByValue(String value){
        EnumWatchType enums[] = EnumWatchType.values();
        for(EnumWatchType enumOne : enums){
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
