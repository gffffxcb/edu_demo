package com.mgh.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author MGH
 * @create 2022-0224 10:26 上午
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //剔除自动配置数据源
@ComponentScan("com.mgh")
@EnableDiscoveryClient
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class,args);
    }
}
