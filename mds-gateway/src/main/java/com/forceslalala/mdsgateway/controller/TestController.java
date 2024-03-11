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
}
