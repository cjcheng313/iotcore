package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

/**
 * 评价类型
 */
@ApiModel("评价类型")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumEvaluate implements BaseEnum<Integer> {
    COMMON(1,"普通评价"),
    MERCHANT(2,"商户评价");
    private final Integer value;
    private final String text;

    private EnumEvaluate(Integer value, String text) {
        this.value = value;
        this.text = text;
    }
    public static EnumEvaluate getByValue(String value){
        EnumEvaluate enums[] = EnumEvaluate.values();
        for(EnumEvaluate enumOne : enums){
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
