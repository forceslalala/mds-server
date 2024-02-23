package com.forceslalala.mdsgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName TestProvider
 * @Author: wangtao
 * @Date: 2023/11/24 17:41
 * @Describe:
 */

@RestController
@RequestMapping("/provider")
@RefreshScope
public class TestController {
//    @Value("${user.username}")
//    private String name;
//
//    @Value("${user.age}")
//    private String age;

    @Value("${blog.data}")
    private String blogName;

    @GetMapping("/get")
    public String get() {
        return "ConfigController#get blog name = " + blogName;
    }
}
