package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 业务结果
 */
@ApiModel("业务结果")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumBusiStatus implements BaseEnum<Integer> {
    INVALID(0,""),
    RECORDED(1,"入账成功"),
    DEBIT(2,"扣款成功"),
    REFUND(3,"系统退款");
    @ApiModelProperty("值")
    private final Integer value;
    @ApiModelProperty("文本")
    private final String text;

    private EnumBusiStatus(Integer value, String text) {
        this.value = value;
        this.text = text;
    }
    public static EnumBusiStatus getByValue(String value){
        EnumBusiStatus enums[] = EnumBusiStatus.values();
        for(EnumBusiStatus enumOne : enums){
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
