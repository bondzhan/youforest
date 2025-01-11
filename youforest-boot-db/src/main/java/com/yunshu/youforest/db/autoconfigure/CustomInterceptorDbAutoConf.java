package com.yunshu.youforest.db.autoconfigure;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.yunshu.youforest.db.interceptor.PoMetaObjectHandler;
import com.yunshu.youforest.db.interceptor.YouforestTenentHandler;
import com.yunshu.youforest.db.properties.YouforestMybatisPlusProperties;
import com.yunshu.youforest.db.properties.YouforestTenantProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @Author Bond
 * @Date 2025/1/8
 * @Description TODO
 */
@Slf4j
@AutoConfiguration
@Import({
        YouforestTenantProperties.class,
        YouforestMybatisPlusProperties.class
})
public class CustomInterceptorDbAutoConf {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(YouforestMybatisPlusProperties mybatisPlusProperties, YouforestTenantProperties tenantProperties) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        if(tenantProperties.isEnabled()) {
            log.info("开启多租户插件");
            interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(new YouforestTenentHandler(tenantProperties)));
        }
        if (mybatisPlusProperties.getPagination().isEnabled()) {
            log.info("开启分页插件");
            interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.getDbType("mysql")));
        }
        if(mybatisPlusProperties.getGlobalConfig().getDbConfig().getSqlSafe().isEnabled()){
            log.info("开启SQL安全插件 全表删除，全表更新");
            interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        }
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "mybatis-plus.global-config.db-config.auto-fill-field", name = "enabled", matchIfMissing = true)
    public MetaObjectHandler metaObjectHandler(YouforestMybatisPlusProperties mybatisPlusProperties) {
        log.info("开启自动填充字段插件");
        return new PoMetaObjectHandler(mybatisPlusProperties);
    }
}
