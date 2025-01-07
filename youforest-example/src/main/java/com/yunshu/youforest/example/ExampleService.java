package com.yunshu.youforest.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author Bond
 * @Date 2025/1/7
 * @Description TODO
 */
@Slf4j
@Component
public class ExampleService {

    public void test() {
        log.info("ExampleService test success");
    }

    public void testzero() {
        log.info(" ExampleService testzero success");
    }
}
