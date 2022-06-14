package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * 直播状态
 * 直播状态只能是0直播未开始1直播中2直播结束3直播停止
 */
@ApiModel(value = "直播状态",description = "直播状态只能是1直播未开始2直播中3直播结束")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumLiveStatus implements BaseEnum<Integer>, Serializable {


    NOT_BEGIN(1,"直播未开始"),
    ALREADY_BEGIN(2,"直播已开始"),
    FINISH(3,"直播结束");
    private final Integer value;
    private final String text;

    private EnumLiveStatus(Integer value, String text) {
        this.value = value;
        this.text = text;
    }
    public static EnumLiveStatus getByValue(String value){
        EnumLiveStatus enums[] = EnumLiveStatus.values();
        for(EnumLiveStatus enumOne : enums){
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
