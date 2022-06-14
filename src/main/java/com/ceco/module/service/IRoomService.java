package com.ceco.module.service;

import com.ceco.channel.app.model.resp.RoomResp;
import com.ceco.module.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 房间管理 服务类
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
public interface IRoomService extends IService<Room> {

    /**
     * 查询用的房间信息
     * @param appUserId
     * @return
     */
    List<RoomResp> selectHomeRoom(String appUserId);


}
