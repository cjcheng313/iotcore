package com.ccj.channel.service.impl;

import com.ccj.channel.admin.model.req.CountryListReq;
import com.ccj.channel.admin.model.req.CountrySaveReq;
import com.ccj.channel.admin.model.resp.CountryResp;
import com.ccj.channel.service.IApiCountryService;
import com.ccj.common.utils.ConvertUtil;
import com.ccj.common.utils.ValidatorUtils;
import com.ccj.module.entity.Country;
import com.ccj.module.service.ICountryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiCountryServiceImpl implements IApiCountryService {

    @Autowired
    ICountryService countryService;

    @Override
    public boolean save(CountrySaveReq req) {
        ValidatorUtils.validateEntity(req);
        Country country = ConvertUtil.convert(req,Country.class);
        return countryService.saveOrUpdate(country);
    }

    @Override
    public PageInfo<CountryResp> list(CountryListReq req) {
        PageHelper.startPage(req.getPageNum(),req.getPageSize());
        List<Country> countryList = countryService.list();
        List<CountryResp> countryRespList =  ConvertUtil.convert(countryList,CountryResp.class);
        return new PageInfo<>(countryRespList);
    }

    @Override
    public List<CountryResp> list() {
        List<Country> countryList = countryService.list();
        List<CountryResp> countryRespList =  ConvertUtil.convert(countryList,CountryResp.class);
        return countryRespList;
    }

    @Override
    public boolean delete(String id) {
        return countryService.removeById(id);
    }
}
