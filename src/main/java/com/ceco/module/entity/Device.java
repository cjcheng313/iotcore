package com.ccj.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.ccj.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 设备信息表
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ce_device")
public class Device extends BaseEntity {


    /**
     * 设备名称
     */
    private String name;

    /**
     * 序列号
     */
    private String serialNo;


    /**
     * 设备绑定用户
     */
    private String appUserId;




}
