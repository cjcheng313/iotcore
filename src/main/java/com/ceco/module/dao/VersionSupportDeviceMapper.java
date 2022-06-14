package com.ceco.module.dao;

import com.ceco.channel.admin.model.resp.DeviceModelResp;
import com.ceco.module.entity.VersionSupportDevice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 固件支持设备型号 Mapper 接口
 * </p>
 *
 * @author zmj
 * @since 2021-10-13
 */
@Mapper
public interface VersionSupportDeviceMapper extends BaseMapper<VersionSupportDevice> {

    /**
     * 根据固件版本查询支持的设备类型信息
     * @param versionIdList
     * @return
     */
    List<DeviceModelResp> selectSupportDeviceList(List<String> versionIdList);
}
