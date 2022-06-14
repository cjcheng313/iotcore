package com.ceco.module.service.impl;

import com.ceco.module.entity.ModelConf;
import com.ceco.module.dao.ModelConfMapper;
import com.ceco.module.service.IModelConfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 模式配置 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Service
public class ModelConfServiceImpl extends ServiceImpl<ModelConfMapper, ModelConf> implements IModelConfService {

}
