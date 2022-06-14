package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * 商户业务类型枚举
 * 1商户充值2.提现3.退款4.打赏
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel(value = "商户业务类型枚举",description = "1商户充值2.提现3.退款4.打赏")
public enum EnumSysBusinessType implements BaseEnum<Integer> , Serializable {
    PAY(1,"充值"),
    WITHDRAW(2,"提现"),
    REFUND(3,"退款"),
    REWARD(4,"打赏");
    private final Integer value;
    private final String text;

    private EnumSysBusinessType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }
    public static EnumSysBusinessType getByValue(String value){
        EnumSysBusinessType businessTypes[] = EnumSysBusinessType.values();
        for(EnumSysBusinessType businessType : businessTypes){
            if(businessType.getValue().equals(value)){
                return businessType;
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
