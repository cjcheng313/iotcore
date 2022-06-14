package com.ccj.module.dao;

import com.ccj.channel.admin.model.resp.DeviceTypeResp;
import com.ccj.module.entity.DeviceModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 设备型号管理 Mapper 接口
 * </p>
 *
 * @author zmj
 * @since 2021-10-13
 */
@Mapper
public interface DeviceModelMapper extends BaseMapper<DeviceModel> {

    List<DeviceTypeResp> listDeviceType();
}
