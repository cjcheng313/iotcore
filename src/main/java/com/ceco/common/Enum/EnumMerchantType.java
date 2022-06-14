package com.ceco.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

/**
 * 商户枚举类型
 * 1：企业用户2代理商3个人
 */
@ApiModel(value = "商户枚举类型",description = "1：企业用户2代理商3个人" )
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumMerchantType implements BaseEnum<Integer> {
    COMPANY(1,"企业商户"),
    AGENT_ID(2,"代理商户"),
    PERSONAGE(3,"个人商户");
    private final Integer value;
    private final String text;

    private EnumMerchantType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }
    public static EnumMerchantType getByValue(String value){
        EnumMerchantType enums[] = EnumMerchantType.values();
        for(EnumMerchantType enumOne : enums){
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
