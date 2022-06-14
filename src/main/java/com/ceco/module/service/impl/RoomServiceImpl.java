package com.ccj.module.service.impl;

import com.ccj.channel.app.model.resp.RoomResp;
import com.ccj.module.entity.Room;
import com.ccj.module.dao.RoomMapper;
import com.ccj.module.service.IRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 房间管理 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

    @Override
    public List<RoomResp> selectHomeRoom(String appUserId) {
        return baseMapper.selectHomeRoom(appUserId);
    }
}
