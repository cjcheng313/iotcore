package com.ceco.channel.admin.controller;


import com.ceco.channel.admin.model.req.ModelSaveReq;
import com.ceco.channel.admin.model.req.ModelListReq;
import com.ceco.channel.admin.model.resp.ModelResp;
import com.ceco.channel.service.IApiModelService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = {"pc端模式配置控制器"})
@RequestMapping("/pc/model")
public class ModelController {

    @Autowired
    IApiModelService apiModelService;
    @ApiOperation("后台保存信息模式配置")
    @PostMapping("/save")
    public boolean save(@RequestBody ModelSaveReq req){
        return apiModelService.save(req);
    }

    @ApiOperation("后台查询模式配置信息")
    @GetMapping("/page/list")
    public PageInfo<ModelResp> list(ModelListReq req){
        return apiModelService.list(req);
    }

    @ApiOperation("删除模式配置")
    @GetMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id){
        return apiModelService.delete(id);
    }

}
