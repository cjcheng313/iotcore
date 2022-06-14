package com.ceco.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("ce_panel_support_device")
public class PanelSupportDevice extends BaseEntity {


    /**
     * 固件版本
     */
    private String panelId;

    /**
     * 设备型号
     */
    private String deviceModelId;

}
