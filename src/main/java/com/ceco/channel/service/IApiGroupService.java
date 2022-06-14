package com.ccj.channel.service;

import com.ccj.channel.app.model.req.*;
import com.ccj.channel.app.model.resp.GroupResp;
import com.ccj.channel.app.model.resp.RoomResp;
import com.github.pagehelper.PageInfo;


public interface IApiGroupService {

    /**
     * 保存群组
     * @param req 群组信息保存请求对象
     * @return 操作结果
     */
    boolean save(GroupSaveReq req);

    /**
     * 查询群组信息列表
     * @param req 群组信息列表请求对象
     * @return
     */
    PageInfo<GroupResp> list(GroupListReq req);

    /**
     * 删除群组数据
     * @param id 删除数据id
     * @return
     */
    boolean delete(String id);


    /**
     * 添加群组设备
     * @param req
     * @return
     */
    boolean addGroupDevice(GroupDeviceAddReq req);
}
