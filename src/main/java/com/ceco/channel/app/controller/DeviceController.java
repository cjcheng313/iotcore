package com.ceco.channel.app.controller;


import com.ceco.channel.app.model.req.DeviceRegisterReq;
import com.ceco.channel.app.model.resp.ConnectedDeviceResp;
import com.ceco.channel.service.IApiDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"app端设备控制器"})
@RequestMapping("/app/device")
public class DeviceController {


    @Autowired
    IApiDeviceService apiDeviceService;


    @PostMapping("/register")
    @ResponseBody
    @ApiOperation(value = "设备注册")
    public boolean register(@RequestBody DeviceRegisterReq req) {
        return apiDeviceService.register(req);
    }


    @PostMapping("/getConnectedDevice/{appUserId}")
    @ResponseBody
    @ApiOperation(value = "已连接设备查询")
    public ConnectedDeviceResp getConnectedDevice(@PathVariable("appUserId") String appUserId){
        return apiDeviceService.getConnectedDevice(appUserId);
    }


}
