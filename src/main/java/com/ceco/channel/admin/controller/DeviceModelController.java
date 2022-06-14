package com.ccj.channel.admin.controller;


import com.ccj.channel.admin.model.req.DeviceModelListReq;
import com.ccj.channel.admin.model.req.DeviceModelSaveReq;
import com.ccj.channel.admin.model.resp.DeviceModelResp;
import com.ccj.channel.admin.model.resp.DeviceTypeResp;
import com.ccj.channel.service.IApiDeviceModelService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(tags = {"pc端设备类型控制器"})
@RequestMapping("/pc/deviceModel")
public class DeviceModelController {

    @Autowired
    IApiDeviceModelService apiDeviceModelService;

    @ApiOperation("后台保存信息设备类型")
    @PostMapping("/save")
    public boolean save(@RequestBody DeviceModelSaveReq req){
        return apiDeviceModelService.save(req);
    }

    @ApiOperation("后台查询分页设备类型信息")
    @GetMapping("/page/list")
    public PageInfo<DeviceModelResp> list(DeviceModelListReq req){
        return apiDeviceModelService.list(req);
    }

    @ApiOperation("查询设备型号信息")
    @GetMapping("/list")
    public List<DeviceModelResp> list(){
        return apiDeviceModelService.list();
    }


    @ApiOperation("删除设备类型")
    @GetMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id){
        return apiDeviceModelService.delete(id);
    }


    @ApiOperation("查询已有设备类型")
    @GetMapping("/listDeviceType")
    public List<DeviceTypeResp> listDeviceType(){
        return apiDeviceModelService.listDeviceType();
    }

}
