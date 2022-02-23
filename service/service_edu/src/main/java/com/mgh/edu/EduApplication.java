package com.mgh.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author MGH
 * @create 2022-0209 9:00 下午
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.mgh"})
@EnableDiscoveryClient //nacos注册
@EnableFeignClients //nacos服务调用
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
