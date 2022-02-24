package com.mgh.commanUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author MGH
 * @create 2022-0223 10:29 下午
 *
 * 自定义注解 @JwtToken加上该注解的接口需要登录才能访问
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JwtToken {
    boolean required() default true;
}
