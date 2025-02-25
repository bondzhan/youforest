package com.yunshu.youforest.web.autoconfigure;

import cn.hutool.core.collection.CollectionUtil;
import com.yunshu.youforest.web.properties.CorsProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * @Author bond
 * @Date 2025/1/4
 **/
@EnableConfigurationProperties(CorsProperties.class)
@ConditionalOnProperty(prefix = CorsProperties.PREFIX,name = "enabled",matchIfMissing = true)
public class CorsAutoConf {
    /**
     * 跨域过滤器
     * @return
     */
    @Bean
    public CorsFilter corsFilter(CorsProperties corsProperties) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        buildCorsConfiguration(corsProperties,corsConfiguration);
        corsConfiguration.setAllowCredentials(corsProperties.getAllowCredentials());
        source.registerCorsConfiguration(corsProperties.getPath(), corsConfiguration);
        return new CorsFilter(source);
    }
    private void buildCorsConfiguration(CorsProperties corsProperties,CorsConfiguration corsConfiguration){
        if(CollectionUtil.isEmpty(corsProperties.getAllowedHeader())){
            corsConfiguration.addAllowedHeader("*");
        }else {
            corsConfiguration.setAllowedHeaders(corsProperties.getAllowedHeader());
        }
        if(CollectionUtil.isEmpty(corsProperties.getAllowedMethod())){
            corsConfiguration.addAllowedMethod("*");
        }else {
            corsConfiguration.setAllowedMethods(corsProperties.getAllowedMethod());
        }
        if(CollectionUtil.isEmpty(corsProperties.getAllowedOrigin())){
            corsConfiguration.addAllowedOrigin("*");
        }else {
            corsConfiguration.setAllowedOrigins(corsProperties.getAllowedOrigin());
        }
        if(CollectionUtil.isNotEmpty(corsProperties.getExposedHeaders())){
            corsConfiguration.setExposedHeaders(corsProperties.getExposedHeaders());
        }
        corsConfiguration.setMaxAge(corsProperties.getMaxAge());
    }
}
