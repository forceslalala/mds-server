package com.forceslalala.mdsuser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @ClassName SwaggerConfig
 * @Author: wangtao
 * @Date: 2024/2/26 14:42
 * @Describe:
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // 创建swagger bean
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                // swagger信息
                .apiInfo(apiInfo())
                // 配置是否开启swagger，若为false，则浏览器不能访问
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.forceslalala.mdsuser.controller"))
                .build();
    }

    // swagger文档信息
    public ApiInfo apiInfo() {
        Contact contact = new Contact(
                "forceslalala",
                "https://github.com/forceslalala/mds-server",
                "forceslalala@gmail.com");
        return new ApiInfo(
                "mds-user api",
                "用户模块api",
                "1.0",
                "",
                contact,
                "",
                "",
                new ArrayList());
    }
}