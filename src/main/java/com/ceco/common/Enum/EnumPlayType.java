package com.ceco.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

/**
 *播放类型
 */
@ApiModel(value = "播放类型",description = "播放类型,1,直播，2，点播")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumPlayType implements BaseEnum<Integer> {
    LIVE(1,"直播"),
    BUNCH(2,"点播");
    private final Integer value;
    private final String text;

    private EnumPlayType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }
    public static EnumPlayType getByValue(String value){
        EnumPlayType enums[] = EnumPlayType.values();
        for(EnumPlayType enumOne : enums){
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
