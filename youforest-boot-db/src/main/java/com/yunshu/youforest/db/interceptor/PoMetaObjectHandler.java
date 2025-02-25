package com.yunshu.youforest.db.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yunshu.youforest.db.properties.YouforestMybatisPlusProperties;
import com.yunshu.youforest.db.properties.YouforestTenantProperties;
import com.yunshu.youforest.web.common.LoginContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Date;

/**
 * @Author Bond
 * @Date 2025/1/8
 * @Description 元对象字段填充,实现公共字段自动写入
 */
@Slf4j
public class PoMetaObjectHandler implements MetaObjectHandler {

    private YouforestMybatisPlusProperties mybatisPlusProperties;

    public PoMetaObjectHandler(YouforestMybatisPlusProperties mybatisPlusProperties) {
        this.mybatisPlusProperties = mybatisPlusProperties;
    }
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, mybatisPlusProperties.getGlobalConfig().getDbConfig().getAutoFillField().getCreateName(), String.class, StringUtils.defaultIfBlank(LoginContextHolder.currentUserId(), "0000"));
        this.strictInsertFill(metaObject, mybatisPlusProperties.getGlobalConfig().getDbConfig().getAutoFillField().getCreateTime(), Date.class, Date.from(Instant.now()));
//        this.updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, mybatisPlusProperties.getGlobalConfig().getDbConfig().getAutoFillField().getModifyName(), String.class, StringUtils.defaultIfBlank(LoginContextHolder.currentUserId(), "0000"));
        this.strictUpdateFill(metaObject, mybatisPlusProperties.getGlobalConfig().getDbConfig().getAutoFillField().getModifyTime(), Date.class, Date.from(Instant.now()));
    }
}