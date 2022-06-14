package com.ceco.module.service.impl;

import com.ceco.channel.admin.model.dto.CountryGuideDto;
import com.ceco.module.entity.CountryGuide;
import com.ceco.module.dao.CountryGuideMapper;
import com.ceco.module.service.ICountryGuideService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 引导配置表使用国家 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Service
public class CountryGuideServiceImpl extends ServiceImpl<CountryGuideMapper, CountryGuide> implements ICountryGuideService {

    @Override
    public List<CountryGuideDto> selectCountryByGuide(List<String> guideIdList) {
        return baseMapper.selectCountryByGuide(guideIdList);
    }
}
