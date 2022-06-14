package com.ceco.module.service.impl;

import com.ceco.module.entity.UserDevice;
import com.ceco.module.dao.UserDeviceMapper;
import com.ceco.module.service.IUserDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户设备 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Service
public class UserDeviceServiceImpl extends ServiceImpl<UserDeviceMapper, UserDevice> implements IUserDeviceService {

}
