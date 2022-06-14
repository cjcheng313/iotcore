package com.ceco.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.ceco.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 设备面板管理
 * </p>
 *
 * @author zmj
 * @since 2021-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ce_device_panel")
public class DevicePanel extends BaseEntity {



    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 支持面板:1.彩光、2白光、3、光效、4、音乐5、节律5、膜片
     */
    private Integer supportPanel;





}
