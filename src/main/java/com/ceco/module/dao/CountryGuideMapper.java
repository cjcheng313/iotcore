package com.ceco.module.dao;

import com.ceco.channel.admin.model.dto.CountryGuideDto;
import com.ceco.module.entity.CountryGuide;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 引导配置表使用国家 Mapper 接口
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Mapper
public interface CountryGuideMapper extends BaseMapper<CountryGuide> {

    List<CountryGuideDto> selectCountryByGuide(@Param("guideIdList") List<String> guideIdList);

}
