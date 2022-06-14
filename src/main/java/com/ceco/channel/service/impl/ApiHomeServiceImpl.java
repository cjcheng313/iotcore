package com.ceco.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ceco.channel.admin.model.req.SceneListReq;
import com.ceco.channel.admin.model.req.SceneSaveReq;
import com.ceco.channel.admin.model.resp.SceneResp;
import com.ceco.channel.app.model.req.*;
import com.ceco.channel.app.model.resp.DeviceResp;
import com.ceco.channel.app.model.resp.HomeResp;
import com.ceco.channel.app.model.resp.RoomResp;
import com.ceco.channel.service.IApiHomeService;
import com.ceco.channel.service.IApiSceneService;
import com.ceco.common.exception.BusinessException;
import com.ceco.common.utils.ConvertUtil;
import com.ceco.common.utils.ValidatorUtils;
import com.ceco.module.entity.*;
import com.ceco.module.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ApiHomeServiceImpl implements IApiHomeService {

    @Autowired
    IHomeService homeService;

    @Autowired
    IRoomService roomService;

    @Autowired
    IRoomDeviceService roomDeviceService;

    @Autowired
    IDeviceService deviceService;


    @Override
    public boolean save(HomeSaveReq req) {
        ValidatorUtils.validateEntity(req);
        Home home = ConvertUtil.convert(req, Home.class);
        boolean result =  homeService.saveOrUpdate(home);
        return result;

    }

    @Override
    public PageInfo<HomeResp> list(HomeListReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<Home> home = homeService.list(new QueryWrapper<Home>().lambda().eq(Home::getAppUserId,req.getAppUserId()));
        List<HomeResp> homeRespList = ConvertUtil.convert(home, HomeResp.class);
        if(!CollectionUtils.isEmpty(homeRespList)){
            List<RoomResp> roomRespList = roomService.selectHomeRoom(req.getAppUserId());
            if(!roomRespList.isEmpty()) {
                Map<String, List<RoomResp>> roomListMap = roomRespList.stream().collect(Collectors.toMap(RoomResp::getHomeId,roomResp-> Lists.newArrayList(roomResp),(List<RoomResp> newValueList, List<RoomResp> oldValueList) ->
                {
                    oldValueList.addAll(newValueList);
                    return oldValueList;
                }));
                homeRespList.forEach(homeResp -> {
                    if(roomListMap.get(homeResp.getId()) !=null){
                        homeResp.setRoomRespList(roomListMap.get(homeResp.getId()));
                    }
                });
            }
        }
        return new PageInfo<>(homeRespList);

    }

    @Override
    public boolean delete(String id) {
        return homeService.removeById(id);
    }

    @Override
    public boolean saveRoom(RoomSaveReq req) {
        ValidatorUtils.validateEntity(req);
        Home home = homeService.getById(req.getHomeId());
        if(home == null){
            throw  new BusinessException("当前家庭信息不存在");
        }
        Room room = ConvertUtil.convert(req, Room.class);
        room.setAppUserId(home.getAppUserId());
        boolean result =  roomService.saveOrUpdate(room);
        return result;
    }

    @Override
    public PageInfo<RoomResp> listRoom(RoomListReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<Room> roomList = roomService.list(new QueryWrapper<Room>().lambda().eq(Room::getHomeId,req.getHomeId()));
        List<RoomResp> roomRespList =  ConvertUtil.convert(roomList,RoomResp.class);
        if(!CollectionUtils.isEmpty(roomRespList)){
            List<DeviceResp> deviceRespList = deviceService.selectGroupDevice(req.getHomeId());
            if(!CollectionUtils.isEmpty(deviceRespList)){
                Map<String, List<DeviceResp>> roomDeviceMap = deviceRespList.stream().collect(Collectors.toMap(DeviceResp::getGroupId,deviceResp-> Lists.newArrayList(deviceResp),(List<DeviceResp> newValueList, List<DeviceResp> oldValueList) ->
                {
                    oldValueList.addAll(newValueList);
                    return oldValueList;
                }));
                roomRespList.forEach(groupResp -> {
                    if(roomDeviceMap.get(groupResp.getId()) !=null){
                        groupResp.setDeviceRespList(roomDeviceMap.get(groupResp.getId()));
                    }
                });
            }
        }
        return new PageInfo<>(roomRespList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRoom(String id) {
        boolean result =  roomService.removeById(id);
        List<Room> roomList = roomService.list(new QueryWrapper<Room>().lambda().eq(Room::getHomeId,id));
        if(!CollectionUtils.isEmpty(roomList)){
            List<String> roomIdList = roomList.stream().map(Room::getId).collect(Collectors.toList());
            result = roomService.removeByIds(roomIdList);
            result = roomDeviceService.remove(new QueryWrapper<RoomDevice>().lambda().in(RoomDevice::getRoomId,roomIdList));
        }
        return result;

    }

    @Override
    public boolean addRoomDevice(RoomDeviceAddReq req) {
        ValidatorUtils.validateEntity(req);
        List<Device> deviceList = deviceService.list(new QueryWrapper<Device>().lambda().in(Device::getId,req.getDeviceIdList()));
        if(!CollectionUtils.isEmpty(deviceList)){
            throw  new BusinessException("设备不存在!");
        }
        roomDeviceService.remove(new QueryWrapper<RoomDevice>().lambda().in(RoomDevice::getDeviceId,req.getDeviceIdList()).eq(RoomDevice::getRoomId,req.getRoomId()));
        List<RoomDevice> roomDeviceList = new ArrayList<>();
        req.getDeviceIdList().forEach(deviceId->{
            RoomDevice roomDevice = new RoomDevice();
            roomDevice.setDeviceId(deviceId);
            roomDevice.setRoomId(req.getRoomId());
            roomDeviceList.add(roomDevice);
        });
        return roomDeviceService.saveBatch(roomDeviceList);
    }
}
