package com.ceco.module.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ceco.common.utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author zmj
 * @since 2021-10-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ce_app_user")
public class AppUser extends BaseEntity {

    private static final long serialVersionUID=1L;



    /**
     * 昵称
     */
    private String nickName;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 1:小程序2app
     */
    private Integer source;

    /**
     * 电话
     */
    private String phone;


    /**
     * 最后一次登录时间
     */
    private Date lastLoginDate;


    private String email;



}
