package com.forceslalala.mdsuser.provider;

import com.forceslalala.mdsuser.dao.UserDao;
import com.forceslalala.mdsuser.entity.User;
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
public class TestProvider {
//    @Value("${user.username}")
//    private String name;
//
//    @Value("${user.age}")
//    private String age;

//    @Value("${blog.name}")
//    private String blogName;

    @Autowired
    private UserDao userDao;

    @GetMapping("/test")
    public String test () {
        List<User> list = userDao.getUserList();
        return "user 服务的返回结果" + list.toString();
    }

}
