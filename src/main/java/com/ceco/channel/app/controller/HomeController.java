package com.ceco.channel.app.controller;

import com.ceco.channel.app.model.req.*;
import com.ceco.channel.app.model.resp.HomeResp;
import com.ceco.channel.app.model.resp.RoomResp;
import com.ceco.channel.service.IApiHomeService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"家庭管理控制器"})
@RequestMapping("/app/home")
public class HomeController {

    @Autowired
    IApiHomeService apiHomeService;

    @ApiOperation("家庭信息保存")
    @PostMapping("/save")
    public boolean save(@RequestBody HomeSaveReq req){
        return apiHomeService.save(req);
    }

    @ApiOperation("家庭列表查询")
    @PostMapping("/list")
    public PageInfo<HomeResp> list(@RequestBody HomeListReq req){
        return apiHomeService.list(req);
    }

    @ApiOperation("删除家庭信息")
    @GetMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id){
        return apiHomeService.delete(id);
    }


    @ApiOperation("家庭房间信息保存")
    @PostMapping("/room/save")
    public boolean saveRoom(@RequestBody RoomSaveReq req){
        return apiHomeService.saveRoom(req);
    }

    @ApiOperation("家庭列表查询")
    @PostMapping("/room/list")
    public PageInfo<RoomResp> listRoom(@RequestBody RoomListReq req){
        return apiHomeService.listRoom(req);
    }

    @ApiOperation("删除家庭信息")
    @GetMapping("/room/delete/{id}")
    public boolean deleteRoom(@PathVariable("id") String id){
        return apiHomeService.delete(id);
    }

    @ApiOperation("添加房间设备")
    @PostMapping("/room/addDevice")
    public boolean addRoomDevice(@RequestBody RoomDeviceAddReq req){
        return apiHomeService.addRoomDevice(req);
    }

}
