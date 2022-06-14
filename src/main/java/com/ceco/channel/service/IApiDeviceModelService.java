package com.ceco.channel.service;

import com.ceco.channel.admin.model.req.DeviceModelListReq;
import com.ceco.channel.admin.model.req.DeviceModelSaveReq;
import com.ceco.channel.admin.model.resp.DeviceModelResp;
import com.ceco.channel.admin.model.resp.DeviceTypeResp;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface IApiDeviceModelService {

    /**
     * 保存设备类型
     * @param req 设备类型信息保存请求对象
     * @return 操作结果
     */
    boolean save(DeviceModelSaveReq req);

    /**
     * 查询设备类型信息列表
     * @param req 设备类型信息列表请求对象
     * @return
     */
    PageInfo<DeviceModelResp> list(DeviceModelListReq req);


    /**
     * 查询设备类型信息列表
     * @return
     */
    List<DeviceModelResp> list();

    /**
     * 删除设备类型数据
     * @param id 删除数据id
     * @return
     */
    boolean delete(String id);

    /**
     * 查询已有的设备类型
     * @return
     */
    List<DeviceTypeResp> listDeviceType();
}
