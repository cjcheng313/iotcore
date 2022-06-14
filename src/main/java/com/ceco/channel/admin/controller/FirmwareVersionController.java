package com.ccj.channel.admin.controller;


import com.ccj.channel.admin.model.req.FirmwareVersionListReq;
import com.ccj.channel.admin.model.req.FirmwareVersionSaveReq;
import com.ccj.channel.admin.model.resp.FirmwareVersionResp;
import com.ccj.channel.service.IApiFirmwareVersionService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(tags = {"pc端固件版本控制器"})
@RequestMapping("/pc/firmwareVersion")
public class FirmwareVersionController {

    @Autowired
    IApiFirmwareVersionService apiFirmwareVersionService;

    @ApiOperation("后台保存信息固件版本")
    @PostMapping("/save")
    public boolean save(@RequestBody FirmwareVersionSaveReq req){
        return apiFirmwareVersionService.save(req);
    }

    @ApiOperation("后台查询分页固件版本信息")
    @GetMapping("/page/list")
    public PageInfo<FirmwareVersionResp> list(FirmwareVersionListReq req){
        return apiFirmwareVersionService.list(req);
    }

    @ApiOperation("查询固件版本信息")
    @GetMapping("/list")
    public List<FirmwareVersionResp> list(){
        return apiFirmwareVersionService.list();
    }


    @ApiOperation("删除固件版本")
    @GetMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id){
        return apiFirmwareVersionService.delete(id);
    }



}
