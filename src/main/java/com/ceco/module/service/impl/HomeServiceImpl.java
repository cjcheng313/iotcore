package com.ceco.module.service.impl;

import com.ceco.module.entity.Home;
import com.ceco.module.dao.HomeMapper;
import com.ceco.module.service.IHomeService;
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
