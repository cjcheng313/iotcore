package com.ccj.channel.service.impl;

import com.ccj.channel.admin.model.req.DeviceModelListReq;
import com.ccj.channel.admin.model.req.DeviceModelSaveReq;
import com.ccj.channel.admin.model.resp.DeviceModelResp;
import com.ccj.channel.admin.model.resp.DeviceTypeResp;
import com.ccj.channel.service.IApiDeviceModelService;
import com.ccj.common.utils.ConvertUtil;
import com.ccj.common.utils.ValidatorUtils;
import com.ccj.module.entity.DeviceModel;
import com.ccj.module.service.IDeviceModelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiDeviceModelServiceImpl implements IApiDeviceModelService {

    @Autowired
    IDeviceModelService deviceModelService;

    @Override
    public boolean save(DeviceModelSaveReq req) {
        ValidatorUtils.validateEntity(req);
        DeviceModel deviceModel = ConvertUtil.convert(req,DeviceModel.class);
        return deviceModelService.saveOrUpdate(deviceModel);
    }

    @Override
    public PageInfo<DeviceModelResp> list(DeviceModelListReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<DeviceModel> deviceModelList = deviceModelService.list();
        List<DeviceModelResp> deviceModelRespList =  ConvertUtil.convert(deviceModelList,DeviceModelResp.class);
        return new PageInfo<>(deviceModelRespList);
    }

    @Override
    public List<DeviceModelResp> list() {
        List<DeviceModel> deviceModelList = deviceModelService.list();
        List<DeviceModelResp> deviceModelRespList =  ConvertUtil.convert(deviceModelList,DeviceModelResp.class);
        return deviceModelRespList;
    }

    @Override
    public boolean delete(String id) {
        return deviceModelService.removeById(id);
    }

    @Override
    public List<DeviceTypeResp> listDeviceType() {
        return deviceModelService.listDeviceType();
    }
}
