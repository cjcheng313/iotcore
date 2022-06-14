package com.ceco.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

/**
 *支付状态
 */
@ApiModel(value = "支付状态",description = "1,待支付,2,已支付,3,取消支付")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumRechargeType implements BaseEnum<Integer> {
    AWAIT(0,"待支付"),
    SUCCESS(1,"已支付"),
    CANCEL(2,"取消支付");

    private final Integer value;
    private final String text;

    private EnumRechargeType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }
    public static EnumRechargeType getByValue(String value){
        EnumRechargeType enums[] = EnumRechargeType.values();
        for(EnumRechargeType enumOne : enums){
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
