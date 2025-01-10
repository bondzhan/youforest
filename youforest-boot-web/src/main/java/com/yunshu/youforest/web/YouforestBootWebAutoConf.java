package com.yunshu.youforest.web;

import com.yunshu.youforest.web.common.TraceIdUtil;
import io.micrometer.tracing.Tracer;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
/**
 * @Author Bond
 * @Date 2025/1/6
 * @Description 自动配置类，用于初始化 Youforest Web 模块的相关配置
 */
@Slf4j
@AutoConfiguration
@PropertySource(value = "classpath:youforest-boot-web.properties", encoding = "UTF8")
@Import({
        com.yunshu.youforest.web.autoconfigure.CorsAutoConf.class,
        com.yunshu.youforest.web.autoconfigure.CustomWebMvcConf.class,
        com.yunshu.youforest.web.autoconfigure.SwaggerAutoConf.class
})
public class YouforestBootWebAutoConf {

    private final Tracer tracer;

    // 通过构造函数注入 Tracer 实例
    public YouforestBootWebAutoConf(Tracer tracer) {
        this.tracer = tracer;
    }
    @PostConstruct
    public void init() {
        TraceIdUtil.setTracer(tracer);
        log.info("Youforest-web version " + YouforestBootWebAutoConf.class.getPackage().getImplementationVersion() + " init success!");
    }

//    @Bean
//    public CommandLineRunner setTracer(Tracer tracer) {
//        return args -> {
//            // 设置全局Tracer，供TraceIdUtil使用
//            TraceIdUtil.setTracer(tracer);
//            log.info("Micrometer Tracer set success!");
//        };
//    }
}
