package com.ceco.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.ceco.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 固件支持设备型号
 * </p>
 *
 * @author zmj
 * @since 2021-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ce_version_support_device")
public class VersionSupportDevice extends BaseEntity {


    /**
     * 固件版本
     */
    private String versionId;

    /**
     * 设备型号
     */
    private String deviceModelId;

}
