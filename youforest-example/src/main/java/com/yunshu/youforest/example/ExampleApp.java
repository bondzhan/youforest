package com.yunshu.youforest.example;

import com.yunshu.youforest.web.model2.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Bond
 * @Date 2025/1/6
 * @Description TODO
 */
@SpringBootApplication
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
