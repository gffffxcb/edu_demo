package com.mgh.order.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MGH
 * @create 2022-0209 9:04 下午
 */
@Configuration
@MapperScan("com.mgh.order.mapper")
public class OrderConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor()); //新版乐观锁插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); //新版分页插件
        return mybatisPlusInterceptor;
    }

  /*  @Bean //分页插件旧版使用方式
    public PaginationInterceptor paginationInterceptor() {
        new PaginationInnerInterceptor()
        return new PaginationInterceptor();
    }*/
}
