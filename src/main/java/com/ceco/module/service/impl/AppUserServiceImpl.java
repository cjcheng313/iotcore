package com.ccj.module.service.impl;

import com.ccj.module.entity.AppUser;
import com.ccj.module.dao.AppUserMapper;
import com.ccj.module.service.IAppUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService {

}
