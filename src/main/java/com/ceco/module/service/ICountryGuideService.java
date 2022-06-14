package com.ccj.module.service;

import com.ccj.channel.admin.model.dto.CountryGuideDto;
import com.ccj.module.entity.CountryGuide;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 引导配置表使用国家 服务类
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
public interface ICountryGuideService extends IService<CountryGuide> {

    /**
     * 查询引导配置使用的国家信息
     * @param guideIdList 引导配置的id
     * @return 国家信息
     */
    List<CountryGuideDto> selectCountryByGuide(List<String> guideIdList);
}
