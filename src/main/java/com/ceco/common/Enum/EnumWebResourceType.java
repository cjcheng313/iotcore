package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "微网站资源类型",description = "1频道,2图片,3图片,")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumWebResourceType implements BaseEnum<Integer>{
    LIVE(1,"直播"),
    VIDEO(2,"视频"),
    IMG(3,"图片");
    private final Integer value;
    private final String text;

    private EnumWebResourceType(Integer value, String text) {
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
