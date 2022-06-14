package com.ceco.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import com.ceco.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 管理用户表
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ce_admin_user")
public class AdminUser extends BaseEntity {

    private static final long serialVersionUID=1L;


    /**
     * 名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;



}
