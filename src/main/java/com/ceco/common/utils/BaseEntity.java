package com.ceco.common.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @TableField(fill = FieldFill.INSERT)
    private String createId;

    @TableField(fill = FieldFill.INSERT)
    private String createName;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateName;

    @TableField(value="create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value="update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 状态:0无效1有效
     */
    private Integer status;

    /**
     * 是否删除：0无效1有效
     */
    private Integer isDelete;


}