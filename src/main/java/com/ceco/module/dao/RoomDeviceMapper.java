package com.ccj.module.dao;

import com.ccj.channel.admin.model.resp.AppUserDeviceResp;
import com.ccj.module.entity.RoomDevice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 房间设备 Mapper 接口
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Mapper
public interface RoomDeviceMapper extends BaseMapper<RoomDevice> {

}
