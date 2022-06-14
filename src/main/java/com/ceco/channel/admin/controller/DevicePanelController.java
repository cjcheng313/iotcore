package com.ceco.channel.admin.controller;


import com.ceco.channel.admin.model.req.DevicePanelListReq;
import com.ceco.channel.admin.model.req.DevicePanelSaveReq;
import com.ceco.channel.admin.model.resp.DevicePanelResp;
import com.ceco.channel.service.IApiDevicePanelService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(tags = {"pc端面板控制器"})
@RequestMapping("/pc/devicePanel")
public class DevicePanelController {

    @Autowired
    IApiDevicePanelService apiDevicePanelService;

    @ApiOperation("后台保存信息面板")
    @PostMapping("/save")
    public boolean save(@RequestBody DevicePanelSaveReq req){
        return apiDevicePanelService.save(req);
    }

    @ApiOperation("后台查询分页面板信息")
    @GetMapping("/page/list")
    public PageInfo<DevicePanelResp> list(DevicePanelListReq req){
        return apiDevicePanelService.list(req);
    }

    @ApiOperation("查询面板信息")
    @GetMapping("/list")
    public List<DevicePanelResp> list(){
        return apiDevicePanelService.list();
    }


    @ApiOperation("删除面板")
    @GetMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id){
        return apiDevicePanelService.delete(id);
    }



}
