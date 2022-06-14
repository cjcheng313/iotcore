package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "视频类型",description = "1视频,2直播回放")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumVideoType implements BaseEnum<Integer>{
    VIDEO(1,"视频"),
    DEMAND(2,"直播回放");

    private final Integer value;
    private final String text;

    private EnumVideoType(Integer value, String text) {
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
