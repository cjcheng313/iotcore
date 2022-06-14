package com.ccj.channel.admin.controller;


import com.ccj.channel.admin.model.req.CountryListReq;
import com.ccj.channel.admin.model.req.CountrySaveReq;
import com.ccj.channel.admin.model.resp.CountryResp;
import com.ccj.channel.service.IApiCountryService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = {"pc端国家控制器"})
@RequestMapping("/pc/country")
public class CountryController {

    @Autowired
    IApiCountryService apiCountryService;

    @ApiOperation("后台保存国家信息")
    @PostMapping("/save")
    public boolean save(@RequestBody CountrySaveReq req){
        return apiCountryService.save(req);
    }

    @ApiOperation("后台查询国家信息")
    @GetMapping("/page/list")
    public PageInfo<CountryResp> list(CountryListReq req){
        return apiCountryService.list(req);
    }

    @ApiOperation("删除国家")
    @GetMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id){
        return apiCountryService.delete(id);
    }

}
