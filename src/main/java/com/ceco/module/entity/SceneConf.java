package com.ccj.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

import com.ccj.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 场景配置
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ce_scene_conf")
public class SceneConf extends BaseEntity {


    /**
     * 模式名称
     */
    private String name;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 显示区域:1白光灯2彩光灯3流光灯
     */
    private Integer showArea;

    /**
     * 亮度
     */
    private String brightnessValue;

    /**
     * 色温值
     */
    private String colorTemperatureValue;

    /**
     * hsv值
     */
    private String hsvValue;




}
