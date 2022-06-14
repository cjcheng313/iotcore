package com.ccj.module.dao;

import com.ccj.channel.app.model.resp.RoomResp;
import com.ccj.module.entity.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 房间管理 Mapper 接口
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Mapper
public interface RoomMapper extends BaseMapper<Room> {


    List<RoomResp> selectHomeRoom(@Param("appUserId") String appUserId);
}
