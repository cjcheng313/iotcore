package com.ccj.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ccj.common.utils.CurrentContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("MybatisMetaObjectHandler")
public class MybatisMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createBy", String.class, CurrentContext.getUid());
        this.strictInsertFill(metaObject, "createName", String.class, CurrentContext.getUname());
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateBy", String.class, CurrentContext.getUid());
        this.strictInsertFill(metaObject, "updateName", String.class, CurrentContext.getUname());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateBy", CurrentContext.getUid(), metaObject);
        this.setFieldValByName("updateName", CurrentContext.getUname(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

}
