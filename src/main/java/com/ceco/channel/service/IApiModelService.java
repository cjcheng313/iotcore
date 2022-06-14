package com.ccj.channel.service;

import com.ccj.channel.admin.model.req.ModelListReq;
import com.ccj.channel.admin.model.req.ModelSaveReq;
import com.ccj.channel.admin.model.resp.ModelResp;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface IApiModelService {

    /**
     * 保存模式
     * @param req 模式信息保存请求对象
     * @return 操作结果
     */
    boolean save(ModelSaveReq req);

    /**
     * 查询模式信息列表
     * @param req 模式信息列表请求对象
     * @return
     */
    PageInfo<ModelResp> list(ModelListReq req);

    /**
     * 查询模式信息列表
     * @return
     */
    List<ModelResp> list();

    /**
     * 删除模式数据
     * @param id 删除数据id
     * @return
     */
    boolean delete(String id);
}
