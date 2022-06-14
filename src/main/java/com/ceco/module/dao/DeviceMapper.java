package com.ccj.module.dao;

import com.ccj.channel.admin.model.resp.AppUserDeviceResp;
import com.ccj.channel.app.model.resp.DeviceResp;
import com.ccj.module.entity.Device;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 设备信息表 Mapper 接口
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Mapper
public interface DeviceMapper extends BaseMapper<Device> {

    List<AppUserDeviceResp> selectUserDetailDevice(@Param("userId") String userId);

    List<DeviceResp> selectGroupDevice(@Param("appUserId") String appUserId);


    List<DeviceResp> selectRoomDevice(@Param("homeId") String homeId);
}
