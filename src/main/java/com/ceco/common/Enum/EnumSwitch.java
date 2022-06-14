package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "开关",description = "0,关闭,1,开启")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum  EnumSwitch implements BaseEnum<Integer>{
    OFF(0,"未启用"),
    ON(1,"启用");
    private final Integer value;
    private final String text;

    private EnumSwitch(Integer value, String text) {
        this.value = value;
        this.text = text;
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
