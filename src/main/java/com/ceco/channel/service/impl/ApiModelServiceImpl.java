package com.ceco.channel.service.impl;

import com.ceco.channel.admin.model.req.ModelListReq;
import com.ceco.channel.admin.model.req.ModelSaveReq;
import com.ceco.channel.admin.model.resp.ModelResp;
import com.ceco.channel.service.IApiModelService;
import com.ceco.common.utils.ConvertUtil;
import com.ceco.common.utils.ValidatorUtils;
import com.ceco.module.entity.ModelConf;
import com.ceco.module.service.IModelConfService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApiModelServiceImpl implements IApiModelService {

    @Autowired
    IModelConfService modelConfService;


    @Override
    public boolean save(ModelSaveReq req) {
        ValidatorUtils.validateEntity(req);
        ModelConf guideConf = ConvertUtil.convert(req, ModelConf.class);
        boolean result =  modelConfService.saveOrUpdate(guideConf);
        return result;

    }

    @Override
    public PageInfo<ModelResp> list(ModelListReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<ModelConf> modelConfListList = modelConfService.list();
        List<ModelResp> countryResps =  ConvertUtil.convert(modelConfListList,ModelResp.class);

        return new PageInfo<>(countryResps);
    }

    @Override
    public List<ModelResp> list() {
        List<ModelConf> modelConfListList = modelConfService.list();
        List<ModelResp> modelRespList =  ConvertUtil.convert(modelConfListList,ModelResp.class);
        return modelRespList;
    }

    @Override
    public boolean delete(String id) {
        return modelConfService.removeById(id);
    }
}
