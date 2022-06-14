package com.ccj.channel.service;

import com.ccj.channel.admin.model.req.GuideListReq;
import com.ccj.channel.admin.model.req.GuideSaveReq;
import com.ccj.channel.admin.model.resp.GuideResp;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface IApiGuideService {

    /**
     * 保存引导
     * @param req 引导信息保存请求对象
     * @return 操作结果
     */
    boolean save(GuideSaveReq req);

    /**
     * 查询引导信息列表
     * @param req 引导信息列表请求对象
     * @return
     */
    PageInfo<GuideResp> list(GuideListReq req);


    /**
     * 查询引导信息列表
     * @return
     */
    List<GuideResp> list();

    /**
     * 删除引导数据
     * @param id 删除数据id
     * @return
     */
    boolean delete(String id);
}
