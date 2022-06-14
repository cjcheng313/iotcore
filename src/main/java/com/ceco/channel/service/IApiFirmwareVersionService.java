package com.ceco.channel.service;

import com.ceco.channel.admin.model.req.FirmwareVersionSaveReq;
import com.ceco.channel.admin.model.req.FirmwareVersionListReq;
import com.ceco.channel.admin.model.resp.FirmwareVersionResp;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IApiFirmwareVersionService {

    /**
     * 保存固件版本
     * @param req 固件版本信息保存请求对象
     * @return 操作结果
     */
    boolean save(FirmwareVersionSaveReq req);

    /**
     * 查询固件版本信息列表
     * @param req 固件版本信息列表请求对象
     * @return
     */
    PageInfo<FirmwareVersionResp> list(FirmwareVersionListReq req);


    /**
     * 查询固件版本信息列表
     * @return
     */
    List<FirmwareVersionResp> list();

    /**
     * 删除固件版本数据
     * @param id 删除数据id
     * @return
     */
    boolean delete(String id);
}
