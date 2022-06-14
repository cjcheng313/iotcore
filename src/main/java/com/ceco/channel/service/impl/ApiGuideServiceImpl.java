package com.ccj.channel.service.impl;

import com.ccj.channel.admin.model.req.GuideListReq;
import com.ccj.channel.admin.model.req.GuideSaveReq;
import com.ccj.channel.admin.model.resp.GuideResp;
import com.ccj.channel.service.IApiGuideService;
import com.ccj.common.utils.ConvertUtil;
import com.ccj.common.utils.ValidatorUtils;
import com.ccj.module.entity.GuideConf;
import com.ccj.module.service.ICountryGuideService;
import com.ccj.module.service.IGuideConfService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiGuideServiceImpl implements IApiGuideService {

    @Autowired
    IGuideConfService guideConfService;

    @Autowired
    ICountryGuideService countryGuideService;

    @Override
    public boolean save(GuideSaveReq req) {
        ValidatorUtils.validateEntity(req);
        GuideConf guideConf = ConvertUtil.convert(req, GuideConf.class);
        boolean result =  guideConfService.saveOrUpdate(guideConf);
        return result;

    }

    @Override
    public PageInfo<GuideResp> list(GuideListReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<GuideConf> guideConfListList = guideConfService.list();
        List<GuideResp> guideRespList =  ConvertUtil.convert(guideConfListList,GuideResp.class);
        return new PageInfo<>(guideRespList);
    }

    @Override
    public List<GuideResp> list() {
        List<GuideConf> guideConfListList = guideConfService.list();
        List<GuideResp> guideRespList =  ConvertUtil.convert(guideConfListList,GuideResp.class);
        return guideRespList;
    }

    @Override
    public boolean delete(String id) {
        return guideConfService.removeById(id);
    }
}
