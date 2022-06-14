package com.ccj.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ccj.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 国家表
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ce_country")
public class Country extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 国家名称
     */
    private String countryName;



}
