package com.ccj.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.ccj.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 群组设备
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ce_group_device")
public class GroupDevice extends BaseEntity {



    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 群组id
     */
    private String groupId;




}
