package com.ceco.module.service;

import com.ceco.channel.admin.model.resp.DeviceTypeResp;
import com.ceco.module.entity.DeviceModel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 设备型号管理 服务类
 * </p>
 *
 * @author zmj
 * @since 2021-10-13
 */
public interface IDeviceModelService extends IService<DeviceModel> {

    /**
     * 查询已保存的设备类型
     * @return
     */
     List<DeviceTypeResp> listDeviceType();
}
