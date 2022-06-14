package com.ceco.channel.admin.controller;


import com.ceco.channel.admin.model.req.SceneListReq;
import com.ceco.channel.admin.model.req.SceneSaveReq;
import com.ceco.channel.admin.model.req.SceneListReq;
import com.ceco.channel.admin.model.resp.SceneResp;
import com.ceco.channel.service.IApiSceneService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(tags = {"pc端场景配置控制器"})
@RequestMapping("/pc/scene")
public class SceneController {

    @Autowired
    IApiSceneService apiSceneService;

    @ApiOperation("后台保存信息场景配置")
    @PostMapping("/save")
    public boolean save(@RequestBody SceneSaveReq req){
        return apiSceneService.save(req);
    }

    @ApiOperation("后台查询场景配置信息")
    @GetMapping("/page/list")
    public PageInfo<SceneResp> list(SceneListReq req){
        return apiSceneService.list(req);
    }

    @ApiOperation("删除场景配置")
    @GetMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") String id){
        return apiSceneService.delete(id);
    }

}
