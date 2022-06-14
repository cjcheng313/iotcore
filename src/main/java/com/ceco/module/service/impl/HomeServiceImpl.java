package com.ccj.module.service.impl;

import com.ccj.module.entity.Home;
import com.ccj.module.dao.HomeMapper;
import com.ccj.module.service.IHomeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 家庭设置 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Service
public class HomeServiceImpl extends ServiceImpl<HomeMapper, Home> implements IHomeService {

}
