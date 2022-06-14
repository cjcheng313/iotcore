package com.ceco.module.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ceco.channel.admin.model.resp.DeviceModelResp;
import com.ceco.module.entity.PanelSupportDevice;

import java.util.List;

/**
 * <p>
 * 固件支持设备型号 服务类
 * </p>
 *
 * @author zmj
 * @since 2021-10-13
 */
public interface IPanelSupportDeviceService extends IService<PanelSupportDevice> {

    /**
     * 查询版本号支持的设备型号
     * @param panelIdList 面板id
     * @return
     */
    List<DeviceModelResp> selectSupportDeviceList(List<String> panelIdList);

}
