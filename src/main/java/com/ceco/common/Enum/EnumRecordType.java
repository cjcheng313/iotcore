package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "登记类型",description = "1电话,2,姓名,3,其他")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumRecordType implements BaseEnum<Integer>{
    PHONE(1,"phone"),
    NAME(2,"name"),
    OTHER(3,"other");

    private final Integer value;
    private final String text;

    private EnumRecordType(Integer value, String text) {
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
