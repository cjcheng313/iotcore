package com.ceco.channel.admin.controller;

import com.alipay.api.domain.UserDetailInfo;
import com.ceco.channel.admin.model.req.AppUserListReq;
import com.ceco.channel.admin.model.req.UserLoginReq;
import com.ceco.channel.admin.model.resp.AppUserDetailResp;
import com.ceco.channel.admin.model.resp.AppUserResp;
import com.ceco.channel.admin.model.resp.UserLoginResp;
import com.ceco.channel.service.IApiUserService;
import com.ceco.common.annotation.NoAuthAnnotation;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = {"pc端用户控制器"})
@RequestMapping("/pc/user")
public class UserController {

    @Autowired
    IApiUserService apiUserService;

    /**
     * 管理端用户密码登录
     * @param req
     * @return
     */
    @PostMapping("/loginByPwd")
    @ResponseBody
    @ApiOperation(value = "管理端用户密码登录")
    @NoAuthAnnotation
    public UserLoginResp loginByPwd(@RequestBody UserLoginReq req) {
        return apiUserService.loginByPwd(req,"admin");
    }



    @ApiOperation("后台查询app用户列表信息")
    @PostMapping("/page/appUserList")
    public PageInfo<AppUserResp> appUserList(@RequestBody AppUserListReq req){
        return apiUserService.appList(req);
    }


    @ApiOperation("后台查询场景配置信息")
    @GetMapping("/appUserDetail/{id}")
    public AppUserDetailResp appUserDetail(@PathVariable("id") String id){
        return apiUserService.appUserDetail(id);
    }


}
