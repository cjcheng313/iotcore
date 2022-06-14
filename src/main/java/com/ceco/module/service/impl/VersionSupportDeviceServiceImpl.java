package com.ccj.module.service.impl;

import com.ccj.channel.admin.model.resp.DeviceModelResp;
import com.ccj.module.entity.VersionSupportDevice;
import com.ccj.module.dao.VersionSupportDeviceMapper;
import com.ccj.module.service.IVersionSupportDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 固件支持设备型号 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2021-10-13
 */
@Service
public class VersionSupportDeviceServiceImpl extends ServiceImpl<VersionSupportDeviceMapper, VersionSupportDevice> implements IVersionSupportDeviceService {


    @Override
    public List<DeviceModelResp> selectSupportDeviceList(List<String> versionIdList) {
        return this.baseMapper.selectSupportDeviceList(versionIdList);
    }
}
