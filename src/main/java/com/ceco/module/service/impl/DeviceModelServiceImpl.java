package com.ceco.module.service.impl;

import com.ceco.channel.admin.model.resp.DeviceTypeResp;
import com.ceco.module.entity.DeviceModel;
import com.ceco.module.dao.DeviceModelMapper;
import com.ceco.module.service.IDeviceModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 设备型号管理 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2021-10-13
 */
@Service
public class DeviceModelServiceImpl extends ServiceImpl<DeviceModelMapper, DeviceModel> implements IDeviceModelService {

    @Override
    public List<DeviceTypeResp> listDeviceType() {
        return baseMapper.listDeviceType();
    }
}
