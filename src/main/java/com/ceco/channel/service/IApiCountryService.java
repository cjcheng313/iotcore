package com.ccj.channel.service;

import com.ccj.channel.admin.model.req.CountryListReq;
import com.ccj.channel.admin.model.req.CountrySaveReq;
import com.ccj.channel.admin.model.resp.CountryResp;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface IApiCountryService {

    /**
     * 保存国家
     * @param req 国家信息保存请求对象
     * @return 操作结果
     */
    boolean save( CountrySaveReq req);

    /**
     * 查询国家信息列表
     * @param req 国家信息列表请求对象
     * @return
     */
    PageInfo<CountryResp> list(CountryListReq req);


    /**
     * 查询国家信息列表
     * @return
     */
    List<CountryResp> list();

    /**
     * 删除国家数据
     * @param id 删除数据id
     * @return
     */
    boolean delete(String id);
}
