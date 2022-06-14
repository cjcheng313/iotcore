package com.ccj.module.service;

import com.ccj.channel.admin.model.resp.DeviceModelResp;
import com.ccj.module.entity.VersionSupportDevice;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 固件支持设备型号 服务类
 * </p>
 *
 * @author zmj
 * @since 2021-10-13
 */
public interface IVersionSupportDeviceService extends IService<VersionSupportDevice> {

    /**
     * 查询版本号支持的设备型号
     * @param versionIdList 版本号
     * @return
     */
    List<DeviceModelResp> selectSupportDeviceList(List<String> versionIdList);

}
