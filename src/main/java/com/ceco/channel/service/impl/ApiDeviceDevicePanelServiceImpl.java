package com.ccj.channel.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccj.channel.admin.model.req.DevicePanelListReq;
import com.ccj.channel.admin.model.req.DevicePanelSaveReq;
import com.ccj.channel.admin.model.resp.DeviceModelResp;
import com.ccj.channel.admin.model.resp.DevicePanelResp;
import com.ccj.channel.service.IApiDevicePanelService;
import com.ccj.common.utils.ConvertUtil;
import com.ccj.common.utils.ValidatorUtils;
import com.ccj.module.entity.DevicePanel;
import com.ccj.module.entity.PanelSupportDevice;
import com.ccj.module.service.IDevicePanelService;
import com.ccj.module.service.IPanelSupportDeviceService;
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
public class ApiDeviceDevicePanelServiceImpl implements IApiDevicePanelService {

    @Autowired
    IDevicePanelService devicePanelService;
    @Autowired
    IPanelSupportDeviceService panelSupportDeviceService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(DevicePanelSaveReq req) {
        ValidatorUtils.validateEntity(req);
        List<DevicePanel> panelList = new ArrayList<>();
        req.getSupportPanelList().forEach(supportPanel ->{
            DevicePanel panel = new DevicePanel();
            panel.setDeviceType(req.getDeviceType());
            panel.setSupportPanel(supportPanel);
            panel.setDeviceType(req.getDeviceType());
        });

        boolean result = devicePanelService.saveBatch(panelList);
        List<PanelSupportDevice> panelSupportDeviceList = new ArrayList<>();
        panelList.forEach(devicePanel -> {
            PanelSupportDevice panelSupportDevice = new PanelSupportDevice();
            panelSupportDevice.setDeviceModelId(req.getDeviceModeId());
            panelSupportDevice.setPanelId(devicePanel.getId());
            panelSupportDeviceList.add(panelSupportDevice);
        });
        result = panelSupportDeviceService.saveBatch(panelSupportDeviceList);
        return result;
    }

    @Override
    public PageInfo<DevicePanelResp> list(DevicePanelListReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<DevicePanel> PanelList = devicePanelService.list();
        List<DevicePanelResp> devicePanelRespList =  ConvertUtil.convert(PanelList, DevicePanelResp.class);
        if(!CollUtil.isEmpty(devicePanelRespList)){
            List<String> panelIdList = devicePanelRespList.stream().map(DevicePanelResp::getId).collect(Collectors.toList());
            List<DeviceModelResp> panelDeviceRespList = panelSupportDeviceService.selectSupportDeviceList(panelIdList);
            if(!CollUtil.isEmpty(panelDeviceRespList)){
                Map<String,List<DeviceModelResp>> panelDeviceMap= panelDeviceRespList.stream().collect(Collectors.toMap(DeviceModelResp::getPanelId, deviceModelResp-> Lists.newArrayList(deviceModelResp),(List<DeviceModelResp> newValueList, List<DeviceModelResp> oldValueList) ->
                {
                    oldValueList.addAll(newValueList);
                    return oldValueList;
                }));
                devicePanelRespList.forEach(DevicePanelResp -> {
                    if(panelDeviceMap.get(DevicePanelResp.getId() ) !=null){
                        DevicePanelResp.setDeviceModelRespList(panelDeviceMap.get(DevicePanelResp.getId()));
                    }
                });
            }
        }
        return new PageInfo<>(devicePanelRespList);
    }

    @Override
    public List<DevicePanelResp> list() {
        List<DevicePanel> PanelList = devicePanelService.list();
        List<DevicePanelResp> devicePanelRespList =  ConvertUtil.convert(PanelList, DevicePanelResp.class);
        if(!CollUtil.isEmpty(devicePanelRespList)){
            List<String> panelIdList = devicePanelRespList.stream().map(DevicePanelResp::getId).collect(Collectors.toList());
            List<DeviceModelResp> panelDeviceRespList = panelSupportDeviceService.selectSupportDeviceList(panelIdList);
            if(!CollUtil.isEmpty(panelDeviceRespList)){
                Map<String,List<DeviceModelResp>> panelDeviceMap= panelDeviceRespList.stream().collect(Collectors.toMap(DeviceModelResp::getPanelId, deviceModelResp-> Lists.newArrayList(deviceModelResp),(List<DeviceModelResp> newValueList, List<DeviceModelResp> oldValueList) ->
                {
                    oldValueList.addAll(newValueList);
                    return oldValueList;
                }));
                devicePanelRespList.forEach(DevicePanelResp -> {
                    if(panelDeviceMap.get(DevicePanelResp.getId() ) !=null){
                        DevicePanelResp.setDeviceModelRespList(panelDeviceMap.get(DevicePanelResp.getId()));
                    }
                });
            }
        }
        return devicePanelRespList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String id) {
        boolean result =  devicePanelService.removeById(id);
        result = panelSupportDeviceService.remove(new QueryWrapper<PanelSupportDevice>().lambda().eq(PanelSupportDevice::getPanelId,id));
        return result;
    }


}
