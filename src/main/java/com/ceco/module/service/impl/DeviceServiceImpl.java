package com.ccj.module.service.impl;

import com.ccj.channel.admin.model.resp.AppUserDeviceResp;
import com.ccj.channel.app.model.resp.DeviceResp;
import com.ccj.module.entity.Device;
import com.ccj.module.dao.DeviceMapper;
import com.ccj.module.service.IDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 设备信息表 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

    @Override
    public List<AppUserDeviceResp> selectUserDetailDevice(String appUserId) {
        return baseMapper.selectUserDetailDevice(appUserId);
    }

    @Override
    public List<DeviceResp> selectGroupDevice(String appUserId) {
        return baseMapper.selectGroupDevice(appUserId);
    }

    @Override
    public List<DeviceResp> selectRoomDevice(String homeId) {
        return baseMapper.selectRoomDevice(homeId);
    }
}
