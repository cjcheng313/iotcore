package com.ceco.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "直播间间资源类型",description = "类型1:引导图;2:广告;3,宣传片,4,背景音乐,5:节目导航条")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumLiveResourceType implements BaseEnum<Integer>{
    GUIDE(1,"引导图"),
    ADVERTISING(2,"广告"),
    PROMOTIONAL(3,"宣传片"),
    BACK_MUSIC(4,"背景音乐"),
   NAVIGATION(5,"节目导航条");

    private final Integer value;
    private final String text;

    private EnumLiveResourceType(Integer value, String text) {
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
