package com.mgh.cms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author MGH
 * @create 2022-0223 9:51 上午
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.mgh"})
@EnableDiscoveryClient //nacos注册
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
