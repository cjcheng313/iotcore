package com.ccj.module.service;

import com.ccj.channel.admin.model.resp.AppUserDeviceResp;
import com.ccj.channel.app.model.resp.DeviceResp;
import com.ccj.module.entity.Device;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 设备信息表 服务类
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
public interface IDeviceService extends IService<Device> {

    /**
     * 用户详情房间设备查询
     * @param appUserId
     * @return
     */
    List<AppUserDeviceResp> selectUserDetailDevice(String appUserId);


    /**
     * 查询用户所有的组及设备信息
     * @param appUserId
     * @return
     */
    List<DeviceResp> selectGroupDevice( String appUserId);


    /**
     * 查询房间的设备信息
     * @param homeId
     * @return
     */
    List<DeviceResp> selectRoomDevice( String homeId);
}
