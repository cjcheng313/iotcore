package com.ceco.channel.service;

import com.ceco.channel.app.model.req.*;
import com.ceco.channel.app.model.resp.HomeResp;
import com.ceco.channel.app.model.resp.RoomResp;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;


public interface IApiHomeService {

    /**
     * 保存家庭
     * @param req 家庭信息保存请求对象
     * @return 操作结果
     */
    boolean save(HomeSaveReq req);

    /**
     * 查询家庭信息列表
     * @param req 家庭信息列表请求对象
     * @return
     */
    PageInfo<HomeResp> list(HomeListReq req);

    /**
     * 删除家庭数据
     * @param id 删除数据id
     * @return
     */
    boolean delete(String id);


    /**
     * 保存房间
     * @param req 房间信息保存请求对象
     * @return 操作结果
     */
    boolean saveRoom(RoomSaveReq req);

    /**
     * 查询房间信息列表
     * @param req 房间信息列表请求对象
     * @return
     */
    PageInfo<RoomResp> listRoom(RoomListReq req);

    /**
     * 删除房间数据
     * @param id 删除数据id
     * @return
     */
    boolean deleteRoom(String id);


    /**
     * 添加房间设备
     * @param req
     * @return
     */
    boolean addRoomDevice( RoomDeviceAddReq req);
}
