package com.ccj.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.ccj.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 设备型号管理
 * </p>
 *
 * @author zmj
 * @since 2021-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ce_device_model")
public class DeviceModel extends BaseEntity {



    /**
     * 设备型号名称
     */
    private String modelName;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 设备图片
     */
    private String imgUrl;

    /**
     * 设备图标
     */
    private String imgIcon;


}
