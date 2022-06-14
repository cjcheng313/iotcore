package com.ceco.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.ceco.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 家庭设置
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ce_home")
public class Home extends BaseEntity {



    /**
     * 家庭名称
     */
    private String name;

    /**
     * 用户id
     */
    private String appUserId;



}
