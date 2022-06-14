package com.ccj.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccj.channel.app.model.req.*;
import com.ccj.channel.app.model.resp.DeviceResp;
import com.ccj.channel.app.model.resp.GroupResp;
import com.ccj.channel.app.model.resp.RoomResp;
import com.ccj.channel.service.IApiGroupService;
import com.ccj.common.exception.BusinessException;
import com.ccj.common.utils.ConvertUtil;
import com.ccj.common.utils.ValidatorUtils;
import com.ccj.module.entity.*;
import com.ccj.module.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ApiGroupServiceImpl implements IApiGroupService {

    @Autowired
    IGroupService groupService;


    @Autowired
    IGroupDeviceService groupDeviceService;


    @Autowired
    IDeviceService deviceService;



    @Override
    public boolean save(GroupSaveReq req) {
        ValidatorUtils.validateEntity(req);
        Group home = ConvertUtil.convert(req, Group.class);
        boolean result =  groupService.saveOrUpdate(home);
        return result;

    }

    @Override
    public PageInfo<GroupResp> list(GroupListReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<Group> groupList = groupService.list(new QueryWrapper<Group>().lambda().eq(Group::getAppUserId,req.getAppUserId()));
        List<GroupResp> groupRespList = ConvertUtil.convert(groupList, GroupResp.class);
        if(!CollectionUtils.isEmpty(groupRespList)){
            List<DeviceResp> deviceRespList = deviceService.selectGroupDevice(req.getAppUserId());
            if(!CollectionUtils.isEmpty(deviceRespList)){
                Map<String, List<DeviceResp>> groupDeviceMap = deviceRespList.stream().collect(Collectors.toMap(DeviceResp::getGroupId,deviceResp-> Lists.newArrayList(deviceResp),(List<DeviceResp> newValueList, List<DeviceResp> oldValueList) ->
                {
                    oldValueList.addAll(newValueList);
                    return oldValueList;
                }));
                groupRespList.forEach(groupResp -> {
                    if(groupDeviceMap.get(groupResp.getId()) !=null){
                        groupResp.setDeviceRespList(groupDeviceMap.get(groupResp.getId()));
                    }
                });
            }
        }
        return new PageInfo<>(groupRespList);

    }

    @Override
    public boolean delete(String id) {
        return groupService.removeById(id);
    }

    @Override
    public boolean addGroupDevice(GroupDeviceAddReq req) {
        ValidatorUtils.validateEntity(req);
        List<Device> deviceList = deviceService.list(new QueryWrapper<Device>().lambda().in(Device::getId,req.getDeviceIdList()));
        if(!CollectionUtils.isEmpty(deviceList)){
            throw  new BusinessException("设备不存在!");
        }
       groupDeviceService.remove(new QueryWrapper<GroupDevice>().lambda().in(GroupDevice::getDeviceId,req.getDeviceIdList()).eq(GroupDevice::getGroupId,req.getGroupId()));
        List<GroupDevice> groupDeviceList = new ArrayList<>();
        req.getDeviceIdList().forEach(deviceId->{
            GroupDevice groupDevice = new GroupDevice();
            groupDevice.setDeviceId(deviceId);
            groupDevice.setGroupId(req.getGroupId());
            groupDeviceList.add(groupDevice);
        });
      return groupDeviceService.saveBatch(groupDeviceList);
    }
}
