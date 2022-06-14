package com.ceco.channel.admin.controller;


import com.ceco.channel.admin.model.req.CountryListReq;
import com.ceco.channel.admin.model.req.CountrySaveReq;
import com.ceco.channel.admin.model.req.GuideListReq;
import com.ceco.channel.admin.model.req.GuideSaveReq;
import com.ceco.channel.admin.model.resp.CountryResp;
import com.ceco.channel.admin.model.resp.GuideResp;
import com.ceco.channel.service.IApiCountryService;
import com.ceco.channel.service.IApiGuideService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = {"pc端引导配置控制器"})
@RequestMapping("/pc/guide")
public class GuideController {

    @Autowired
    IApiGuideService apiGuideService;

    @ApiOperation("后台保存信息引导配置")
    @PostMapping("/save")
    public boolean save(@RequestBody GuideSaveReq req){
        return apiGuideService.save(req);
    }

    @ApiOperation("后台查询引导配置信息")
    @GetMapping("/page/list")
    public PageInfo<GuideResp> list(GuideListReq req){
        return apiGuideService.list(req);
    }

    @ApiOperation("删除引导配置")
    @GetMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id){
        return apiGuideService.delete(id);
    }

}
