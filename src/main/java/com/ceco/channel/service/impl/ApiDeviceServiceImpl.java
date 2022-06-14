package com.ccj.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccj.channel.admin.model.resp.AppUserDeviceResp;
import com.ccj.channel.app.model.req.DeviceRegisterReq;
import com.ccj.channel.app.model.resp.*;
import com.ccj.channel.service.IApiDeviceService;
import com.ccj.common.exception.BusinessException;
import com.ccj.common.utils.ConvertUtil;
import com.ccj.common.utils.ValidatorUtils;
import com.ccj.module.entity.Device;
import com.ccj.module.entity.Group;
import com.ccj.module.entity.Home;
import com.ccj.module.service.IDeviceService;
import com.ccj.module.service.IGroupService;
import com.ccj.module.service.IHomeService;
import com.ccj.module.service.IRoomService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApiDeviceServiceImpl implements IApiDeviceService {

    @Autowired
    IDeviceService deviceService;

    @Autowired
    IHomeService homeService;

    @Autowired
    IRoomService roomService;

    @Autowired
    IGroupService groupService;

    @Override
    public boolean register(DeviceRegisterReq req) {
        ValidatorUtils.validateEntity(req);

        Device device = deviceService.getOne(new QueryWrapper<Device>().lambda().eq(Device::getSerialNo,req.getSerialNo()).eq(Device::getAppUserId,req.getAppUserId()));
        if(device != null){
            throw  new BusinessException("设备已注册！");
        }
        device = ConvertUtil.convert(req,Device.class);
        return deviceService.save(device);
    }

    @Override
    public ConnectedDeviceResp getConnectedDevice(String appUserId) {
        ConnectedDeviceResp connectedDeviceResp = new ConnectedDeviceResp();
        List<Home> home = homeService.list(new QueryWrapper<Home>().lambda().eq(Home::getAppUserId,appUserId));
        List<HomeResp> homeRespList = ConvertUtil.convert(home, HomeResp.class);
        if(!CollectionUtils.isEmpty(homeRespList)){
            List<RoomResp> roomRespList = roomService.selectHomeRoom(appUserId);
            if(!roomRespList.isEmpty()) {
                Map<String, List<RoomResp>> roomListMap = roomRespList.stream().collect(Collectors.toMap(RoomResp::getHomeId,roomResp-> Lists.newArrayList(roomResp),(List<RoomResp> newValueList, List<RoomResp> oldValueList) ->
                {
                    oldValueList.addAll(newValueList);
                    return oldValueList;
                }));
            List<AppUserDeviceResp> appUserDeviceRespList = deviceService.selectUserDetailDevice(appUserId);
            Map<String,List<AppUserDeviceResp>> roomDeviceMap = appUserDeviceRespList.stream().collect(Collectors.toMap(AppUserDeviceResp::getRoomId,appUserDeviceResp-> Lists.newArrayList(appUserDeviceResp),(List<AppUserDeviceResp> newValueList, List<AppUserDeviceResp> oldValueList) ->
            {
                oldValueList.addAll(newValueList);
                return oldValueList;
            }));
            homeRespList.forEach(homeResp -> {
                    if(roomListMap.get(homeResp.getId()) !=null){
                        homeResp.setRoomRespList(roomListMap.get(homeResp.getId()));
                        List<DeviceResp> deviceRespList = new ArrayList<>();
                        homeResp.getRoomRespList().forEach(roomResp -> {
                            if(roomDeviceMap.get(roomResp.getId())!=null){
                                List<AppUserDeviceResp> appUserRoomDeviceRespList = roomDeviceMap.get(roomResp.getId());
                                appUserRoomDeviceRespList.forEach(appUserDeviceResp -> {
                                    DeviceResp deviceResp = new DeviceResp();
                                    deviceResp.setId(appUserDeviceResp.getDeviceId());
                                    deviceResp.setName(appUserDeviceResp.getDeviceName());
                                    deviceResp.setSerialNo(appUserDeviceResp.getSerialNo());
                                    deviceRespList.add(deviceResp);
                                });
                                roomResp.setDeviceRespList(deviceRespList);
                            }
                        });
                    }
                });
            }
        }
        connectedDeviceResp.setHomeRespList(homeRespList);

        List<Group> groupList = groupService.list(new QueryWrapper<Group>().lambda().eq(Group::getAppUserId,appUserId));
        List<GroupResp> groupRespList = ConvertUtil.convert(groupList, GroupResp.class);
        if(!CollectionUtils.isEmpty(groupRespList)){
            List<DeviceResp> deviceRespList = deviceService.selectGroupDevice(appUserId);
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
        connectedDeviceResp.setGroupRespList(groupRespList);
        return connectedDeviceResp;
    }






}
