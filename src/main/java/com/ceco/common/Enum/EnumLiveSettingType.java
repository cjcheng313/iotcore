package com.ccj.common.Enum;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "直播设置类型",description = "类型0：观看权限,1:引导图;2:广告;3,宣传片,4,背景音乐,5:节目导航条,6,登记观看,7,微信分享,8,热度,9,题库，10，关于我们,11,历史直播")
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum EnumLiveSettingType implements BaseEnum<Integer>{
    WATCH(0,"观看权限"),
    GUIDE(1,"引导图"),
    ADVERTISING(2,"广告"),
    PROMOTIONAL(3,"宣传片"),
    BACK_MUSIC(4,"背景音乐"),
    NAVIGATION(5,"节目导航条"),
    REGISTER(6,"登记观看"),
    WX_SHARE(7,"二维码关注"),
    HEAT(8,"热度"),
    QUESTION_BANK(9,"题库功能"),
    LIVE_ABOUT(10,"关于我们"),
    LIVE_RECORD(11,"历史直播"),
    ;

    private final Integer value;
    private final String text;

    private EnumLiveSettingType(Integer value, String text) {
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
