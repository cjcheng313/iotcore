package com.ceco.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

/**
 * @Author: zy
 * @Date: 2020/10/29 14:08
 * @desc：
 */

@ApiModel(value = "消息类型",description = "1,普通消息,2,点赞,3,花朵")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumMessageType implements BaseEnum<Integer>{
    COMMON(1,"普通"),
    GIVE(2,"点赞"),
    GIFT(3,"礼物"),
    UPDAE_DATA(4,"更新页面数据");
    private final Integer value;
    private final String text;

    private EnumMessageType(Integer value, String text) {
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
