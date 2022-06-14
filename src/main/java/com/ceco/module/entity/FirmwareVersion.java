package com.ccj.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.ccj.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 固件版本
 * </p>
 *
 * @author zmj
 * @since 2021-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ce_firmware_version")
public class FirmwareVersion extends BaseEntity {

    /**
     * 名称
     */
    private String name;

    /**
     * 固件key
     */
    private String firmwareKey;

    /**
     * 固件版本
     */
    private String version;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 固件地址
     */
    private String downloadUrl;


}
