package com.ceco.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.ceco.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 房间设备
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ce_room_device")
public class RoomDevice extends BaseEntity {



    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 房间id
     */
    private String roomId;





}
