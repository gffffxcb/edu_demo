package com.mgh.sta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author MGH
 * @create 2022-0301 3:08 下午
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.mgh"})
@EnableDiscoveryClient //nacos注册
@EnableFeignClients //nacos服务调用
@EnableScheduling
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class, args);
    }
}
