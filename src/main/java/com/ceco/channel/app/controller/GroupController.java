package com.ceco.channel.app.controller;

import com.ceco.channel.app.model.req.*;
import com.ceco.channel.app.model.resp.GroupResp;
import com.ceco.channel.app.model.resp.RoomResp;
import com.ceco.channel.service.IApiGroupService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"群组管理控制器"})
@RequestMapping("/app/group")
public class GroupController {

    @Autowired
    IApiGroupService apiGroupService;

    @ApiOperation("群组信息保存")
    @PostMapping("/save")
    public boolean save(@RequestBody GroupSaveReq req){
        return apiGroupService.save(req);
    }

    @ApiOperation("群组列表查询")
    @PostMapping("/list")
    public PageInfo<GroupResp> list(@RequestBody GroupListReq req){
        return apiGroupService.list(req);
    }

    @ApiOperation("删除群组信息")
    @GetMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id){
        return apiGroupService.delete(id);
    }
    
    @ApiOperation("添加房间设备")
    @PostMapping("/room/addDevice")
    public boolean addRoomDevice(@RequestBody GroupDeviceAddReq req){
        return apiGroupService.addGroupDevice(req);
    }

}
