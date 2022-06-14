package com.ceco.channel.app.controller;

import com.ceco.channel.admin.model.resp.CountryResp;
import com.ceco.channel.admin.model.resp.GuideResp;
import com.ceco.channel.admin.model.resp.ModelResp;
import com.ceco.channel.service.IApiCountryService;
import com.ceco.channel.service.IApiGuideService;
import com.ceco.channel.service.IApiModelService;
import com.ceco.channel.service.IApiSceneService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = {"app配置控制类"})
@RequestMapping("/app/config")
public class ConfigController {

    @Autowired
    IApiCountryService apiCountryService;

    @Autowired
    IApiGuideService apiGuideService;

    @Autowired
    IApiModelService apiModelService;

    @Autowired
    IApiSceneService apiSceneService;

    @ApiOperation("查新国家信息")
    @GetMapping("/country")
    public List<CountryResp> country(){
        return apiCountryService.list();
    }

    @ApiOperation("查询引导页")
    @GetMapping("/guide")
    public List<GuideResp> guide(){
        return apiGuideService.list();
    }

    @ApiOperation("后台查询模式配置信息")
    @GetMapping("/model")
    public List<ModelResp> model(){
        return apiModelService.list();
    }


    @ApiOperation("后台查询场景配置信息")
    @GetMapping("/scene")
    public List<ModelResp> scene(){
        return apiModelService.list();
    }


}
