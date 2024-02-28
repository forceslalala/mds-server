package com.forceslalala.mdsuser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName InterceptorRegister
 * @Author: wangtao
 * @Date: 2024/2/27 17:08
 * @Describe: 拦截器注册
 */

@Configuration
public class InterceptorRegister implements WebMvcConfigurer {
    /**
     * 把我们定义的拦截器类注册为Bean
     */
    @Bean
    public HandlerInterceptor getInterceptor() {
        return new UserInterceptor();
    }

    /**
     * 添加拦截器，并配置拦截地址
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pathPatterns = new ArrayList<>();
        pathPatterns.add("/api/v1/user/login");
        pathPatterns.add("/api/v1/user/register");
        pathPatterns.add("/api/v1/user/isLogin");
        registry.addInterceptor(getInterceptor()).excludePathPatterns(pathPatterns);
    }
}
