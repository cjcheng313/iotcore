package com.ceco.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.ceco.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 房间管理
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ce_room")
public class Room extends BaseEntity {

    /**
     * 房间名称
     */
    private String name;

    /**
     * 用户id
     */
    private String appUserId;

    /**
     * 所属家庭
     */
    private String homeId;



}
