package com.ccj.channel.service;

import com.ccj.channel.app.model.req.DeviceRegisterReq;
import com.ccj.channel.app.model.resp.ConnectedDeviceResp;

public interface IApiDeviceService {

    /**
     * 设备注册
     * @param req
     * @return
     */
    boolean register( DeviceRegisterReq req);


    /**
     * 查询已连接设备信息
     *   @param appUserId
     * @return
     */
    ConnectedDeviceResp getConnectedDevice(String appUserId);
}
