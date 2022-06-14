package com.ccj.channel.service;

import com.ccj.channel.admin.model.req.AppUserListReq;
import com.ccj.channel.admin.model.req.UserLoginReq;
import com.ccj.channel.admin.model.resp.AppUserDetailResp;
import com.ccj.channel.admin.model.resp.AppUserResp;
import com.ccj.channel.admin.model.resp.UserLoginResp;
import com.ccj.channel.app.model.req.AppUserRegisterReq;
import com.ccj.channel.app.model.req.AppUserSaveReq;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;


public interface IApiUserService {

    /**
     * 管理端用户密码登录
     * @param req
     * @return
     */
    UserLoginResp loginByPwd(UserLoginReq req,String systemKey);


    /**
     * app用户列表
     * @param req 筛选条件
     * @return app用户数据
     */
    PageInfo<AppUserResp> appList(AppUserListReq req);


    /**
     * 用户详情
     * @param id
     * @return
     */
    AppUserDetailResp appUserDetail( String id);

    /**
     * 修改app用户信息
     * @param req
     * @return
     */
    boolean saveUserInfo( AppUserSaveReq req);

    /**
     * 用户注册
     * @param req
     * @return
     */
    boolean register( AppUserRegisterReq req);
}
