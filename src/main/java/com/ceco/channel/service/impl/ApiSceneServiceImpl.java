package com.ccj.channel.service.impl;

import com.ccj.channel.admin.model.req.SceneListReq;
import com.ccj.channel.admin.model.req.SceneSaveReq;
import com.ccj.channel.admin.model.resp.SceneResp;
import com.ccj.channel.service.IApiSceneService;
import com.ccj.common.utils.ConvertUtil;
import com.ccj.common.utils.ValidatorUtils;
import com.ccj.module.entity.SceneConf;
import com.ccj.module.service.ISceneConfService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiSceneServiceImpl implements IApiSceneService {

    @Autowired
    ISceneConfService sceneConfService;


    @Override
    public boolean save(SceneSaveReq req) {
        ValidatorUtils.validateEntity(req);
        SceneConf guideConf = ConvertUtil.convert(req, SceneConf.class);
        boolean result =  sceneConfService.saveOrUpdate(guideConf);

        return result;

    }

    @Override
    public PageInfo<SceneResp> list(SceneListReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<SceneConf> guideConfListList = sceneConfService.list();
        List<SceneResp> countryResps =  ConvertUtil.convert(guideConfListList,SceneResp.class);
       
        return new PageInfo<>(countryResps);
    }

    @Override
    public List<SceneResp> list() {
        List<SceneConf> sceneConfList = sceneConfService.list();
        List<SceneResp> sceneRespList =  ConvertUtil.convert(sceneConfList,SceneResp.class);
        return sceneRespList;
    }

    @Override
    public boolean delete(String id) {
        return sceneConfService.removeById(id);
    }
}
