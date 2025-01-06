package com.yunshu.youforest.example;

import com.yunshu.youforest.web.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Bond
 * @Date 2025/1/6
 * @Description
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.yunshu.youforest.web", "com.yunshu.youforest.example"})
@RestController
@Slf4j
public class ExampleApp {
    public static void main(String[] args) {
        SpringApplication.run(ExampleApp.class, args);

    }

    @RequestMapping("/test")
    public ApiResult<String> test() {
        log.info("test success");
        return ApiResult.success("test");
    }

    @RequestMapping("/testzero")
    public ApiResult<String> testzero() {
        log.info("testzero success");
        int a = 1 / 0;
        return ApiResult.success("test");
    }
}
