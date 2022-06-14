package com.ccj.channel.service;

import com.ccj.channel.admin.model.req.DevicePanelListReq;
import com.ccj.channel.admin.model.req.DevicePanelSaveReq;
import com.ccj.channel.admin.model.resp.DevicePanelResp;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IApiDevicePanelService {

    /**
     * 保存面板
     * @param req 面板信息保存请求对象
     * @return 操作结果
     */
    boolean save(DevicePanelSaveReq req);

    /**
     * 查询面板信息列表
     * @param req 面板信息列表请求对象
     * @return
     */
    PageInfo<DevicePanelResp> list(DevicePanelListReq req);


    /**
     * 查询面板信息列表
     * @return
     */
    List<DevicePanelResp> list();

    /**
     * 删除面板数据
     * @param id 删除数据id
     * @return
     */
    boolean delete(String id);
}
