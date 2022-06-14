package com.ccj.channel.app.controller;

import com.ccj.channel.admin.model.req.UserLoginReq;
import com.ccj.channel.admin.model.resp.UserLoginResp;
import com.ccj.channel.app.model.req.AppUserRegisterReq;
import com.ccj.channel.app.model.req.AppUserSaveReq;
import com.ccj.channel.service.IApiUserService;
import com.ccj.common.annotation.NoAuthAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"app端用户控制器"})
@RequestMapping("/app/user")
public class AppUserController {


    @Autowired
    IApiUserService apiUserService;


    @PostMapping("/saveUserInfo")
    @ResponseBody
    @ApiOperation(value = "修改用户信息")
    public boolean saveUserInfo(@RequestBody AppUserSaveReq req) {
        return apiUserService.saveUserInfo(req);
    }


    /**
     * app端用户密码登录
     * @param req
     * @return
     */
    @PostMapping("/loginByPwd")
    @ResponseBody
    @ApiOperation(value = "管理端用户密码登录")
    @NoAuthAnnotation
    public UserLoginResp loginByPwd(@RequestBody UserLoginReq req) {
        return apiUserService.loginByPwd(req,"app");
    }



    /**
     * app端用户注册
     * @param req
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    @ApiOperation(value = "管理端用户密码登录")
    @NoAuthAnnotation
    public boolean register(@RequestBody AppUserRegisterReq req) {
        return apiUserService.register(req);
    }



}
