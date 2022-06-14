package com.ceco.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ceco.channel.admin.model.resp.DeviceModelResp;
import com.ceco.module.entity.PanelSupportDevice;
import org.apache.ibatis.annotations.Mapper;

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
public interface PanelSupportDeviceMapper extends BaseMapper<PanelSupportDevice> {

    /**
     * 根据面板查询支持的设备类型信息
     * @param panelIdList
     * @return
     */
    List<DeviceModelResp> selectSupportDeviceList(List<String> panelIdList);
}
