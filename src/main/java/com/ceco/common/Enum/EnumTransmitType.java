package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "播放类型",description = "1直播，2，点播")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumTransmitType implements BaseEnum<Integer>{
    LIVE(1,"直播"),
    BUNCH(2,"点播");

    private final Integer value;
    private final String text;

    private EnumTransmitType(Integer value, String text) {
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
