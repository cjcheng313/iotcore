package com.ccj.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.ccj.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 引导配置表使用国家
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ce_country_guide")
public class CountryGuide extends BaseEntity {


    /**
     * 引导配置id
     */
    private String guideId;

    /**
     * 国家id
     */
    private String countryId;





}
