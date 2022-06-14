package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

/**
 * 直播间菜单分类
 * 直播间菜单分类 菜单类型1:图文互动,2:互动聊天,3,排行版,4,嵌入网页
 */
@ApiModel(value = "登记信息类型",description ="菜单类型1:图文互动,2:互动聊天,3,排行榜,4,嵌入网页,5,拍照直播")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumLiveMenuType implements BaseEnum<Integer> {
    IMAGE_CONTENT(1,"图文互动"),
    CHAT(2,"互动聊天"),
    RANK(3,"排行榜"),
    EMBED(4,"嵌入网页"),
    PHOTOS(5,"拍照直播"),
    ;
    private final Integer value;
    private final String text;

    private EnumLiveMenuType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }
    public static EnumLiveMenuType getByValue(String value){
        EnumLiveMenuType enums[] = EnumLiveMenuType.values();
        for(EnumLiveMenuType enumOne : enums){
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
