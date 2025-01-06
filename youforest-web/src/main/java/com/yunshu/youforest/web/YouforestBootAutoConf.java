package com.yunshu.youforest.web;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author Bond
 * @Date 2025/1/6
 * @Description TODO
 */
@Slf4j
@ComponentScan(basePackages = {"com.yunshu.youforest.web.interceptor"})
@AutoConfiguration
@PropertySource(value = "classpath:youforest-boot.properties", encoding = "UTF8")
@Import({
        com.yunshu.youforest.web.autoconfigure.CorsAutoConf.class,
        com.yunshu.youforest.web.autoconfigure.CustomWebMvcConf.class,
        com.yunshu.youforest.web.autoconfigure.SwaggerAutoConf.class
})
public class YouforestBootAutoConf {
    @PostConstruct
    public void init() {
        log.info("Youforest-web version" + YouforestBootAutoConf.class.getPackage().getImplementationVersion() + "init success!");
    }
}
