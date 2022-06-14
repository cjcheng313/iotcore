package com.ceco.module.dao;

import com.ceco.module.entity.Country;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 国家表 Mapper 接口
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Mapper
public interface CountryMapper extends BaseMapper<Country> {

}
