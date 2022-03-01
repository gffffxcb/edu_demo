package com.mgh.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author MGH
 * @create 2022-0224 1:55 下午
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.mgh"})
@EnableDiscoveryClient //nacos注册
public class UCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UCenterApplication.class,args);
    }

}
