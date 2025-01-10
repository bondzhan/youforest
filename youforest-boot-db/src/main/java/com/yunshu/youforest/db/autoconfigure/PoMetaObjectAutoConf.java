package com.yunshu.youforest.db.autoconfigure;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.yunshu.youforest.db.interceptor.PoMetaObjectHandler;
import com.yunshu.youforest.db.properties.YouforestMybatisPlusProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @Author Bond
 * @Date 2025/1/8
 * @Description TODO
 */
public class PoMetaObjectAutoConf {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "mybatis-plus.global-config.db-config.auto-fill-field", name = "enabled", matchIfMissing = true)
    public MetaObjectHandler metaObjectHandler() {
        return new PoMetaObjectHandler();
    }
}
