package com.ccj.channel.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccj.channel.admin.model.req.FirmwareVersionListReq;
import com.ccj.channel.admin.model.req.FirmwareVersionSaveReq;
import com.ccj.channel.admin.model.resp.DeviceModelResp;
import com.ccj.channel.admin.model.resp.FirmwareVersionResp;
import com.ccj.channel.admin.model.resp.DeviceTypeResp;
import com.ccj.channel.service.IApiFirmwareVersionService;
import com.ccj.common.utils.ConvertUtil;
import com.ccj.common.utils.ValidatorUtils;
import com.ccj.module.entity.FirmwareVersion;
import com.ccj.module.entity.VersionSupportDevice;
import com.ccj.module.service.IFirmwareVersionService;
import com.ccj.module.service.IVersionSupportDeviceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApiFirmwareVersionServiceImpl implements IApiFirmwareVersionService {

    @Autowired
    IFirmwareVersionService firmwareVersionService;
    @Autowired
    IVersionSupportDeviceService versionSupportDeviceService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(FirmwareVersionSaveReq req) {
        ValidatorUtils.validateEntity(req);
        FirmwareVersion firmwareVersion = ConvertUtil.convert(req,FirmwareVersion.class);
        List<VersionSupportDevice> versionSupportDeviceList = new ArrayList<>();
        req.getDeviceModel().forEach(deviceModel ->{
            VersionSupportDevice versionSupportDevice = new VersionSupportDevice();
            versionSupportDevice.setDeviceModelId(deviceModel);
            versionSupportDevice.setVersionId(firmwareVersion.getId());
            versionSupportDeviceList.add(versionSupportDevice);
        });
        boolean result = firmwareVersionService.saveOrUpdate(firmwareVersion);
        result = versionSupportDeviceService.saveBatch(versionSupportDeviceList);
        return result;
    }

    @Override
    public PageInfo<FirmwareVersionResp> list(FirmwareVersionListReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<FirmwareVersion> firmwareVersionList = firmwareVersionService.list();
        List<FirmwareVersionResp> firmwareVersionRespList =  ConvertUtil.convert(firmwareVersionList,FirmwareVersionResp.class);
        if(!CollUtil.isEmpty(firmwareVersionRespList)){
            List<String> versionIdList = firmwareVersionRespList.stream().map(FirmwareVersionResp::getId).collect(Collectors.toList());
            List<DeviceModelResp> modelRespList = versionSupportDeviceService.selectSupportDeviceList(versionIdList);
            if(!CollUtil.isEmpty(modelRespList)){
                Map<String,List<DeviceModelResp>> versionDeviceMap= modelRespList.stream().collect(Collectors.toMap(DeviceModelResp::getVersionId, deviceModelResp-> Lists.newArrayList(deviceModelResp),(List<DeviceModelResp> newValueList, List<DeviceModelResp> oldValueList) ->
                {
                    oldValueList.addAll(newValueList);
                    return oldValueList;
                }));
                firmwareVersionRespList.forEach(firmwareVersionResp -> {
                    if(versionDeviceMap.get(firmwareVersionResp.getId() ) !=null){
                        firmwareVersionResp.setDeviceModelRespList(versionDeviceMap.get(firmwareVersionResp.getId()));
                    }
                });
            }
        }
        return new PageInfo<>(firmwareVersionRespList);
    }

    @Override
    public List<FirmwareVersionResp> list() {
        List<FirmwareVersion> firmwareVersionList = firmwareVersionService.list();
        List<FirmwareVersionResp> firmwareVersionRespList =  ConvertUtil.convert(firmwareVersionList,FirmwareVersionResp.class);
        if(!CollUtil.isEmpty(firmwareVersionRespList)){
            List<String> versionIdList = firmwareVersionRespList.stream().map(FirmwareVersionResp::getId).collect(Collectors.toList());
            List<DeviceModelResp> modelRespList = versionSupportDeviceService.selectSupportDeviceList(versionIdList);
            if(!CollUtil.isEmpty(modelRespList)){
                Map<String,List<DeviceModelResp>> versionDeviceMap= modelRespList.stream().collect(Collectors.toMap(DeviceModelResp::getVersionId, deviceModelResp-> Lists.newArrayList(deviceModelResp),(List<DeviceModelResp> newValueList, List<DeviceModelResp> oldValueList) ->
                {
                    oldValueList.addAll(newValueList);
                    return oldValueList;
                }));
                firmwareVersionRespList.forEach(firmwareVersionResp -> {
                    if(versionDeviceMap.get(firmwareVersionResp.getId() ) !=null){
                        firmwareVersionResp.setDeviceModelRespList(versionDeviceMap.get(firmwareVersionResp.getId()));
                    }
                });
            }
        }
        return firmwareVersionRespList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String id) {
        boolean result =  firmwareVersionService.removeById(id);
        result = versionSupportDeviceService.remove(new QueryWrapper<VersionSupportDevice>().lambda().eq(VersionSupportDevice::getVersionId,id));
        return result;
    }


}
