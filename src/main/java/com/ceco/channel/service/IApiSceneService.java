package com.ccj.channel.service;

import com.ccj.channel.admin.model.req.SceneListReq;
import com.ccj.channel.admin.model.req.SceneSaveReq;
import com.ccj.channel.admin.model.resp.SceneResp;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface IApiSceneService {

    /**
     * 保存场景
     * @param req 场景信息保存请求对象
     * @return 操作结果
     */
    boolean save(SceneSaveReq req);

    /**
     * 查询场景信息列表
     * @param req 场景信息列表请求对象
     * @return
     */
    PageInfo<SceneResp> list(SceneListReq req);



    /**
     * 查询场景信息列表
     * @return
     */
    List<SceneResp> list();


    /**
     * 删除场景数据
     * @param id 删除数据id
     * @return
     */
    boolean delete(String id);
}
