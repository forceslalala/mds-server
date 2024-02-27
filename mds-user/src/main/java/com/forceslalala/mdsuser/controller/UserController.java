package com.forceslalala.mdsuser.controller;

import com.forceslalala.mdsuser.entity.User;
import com.forceslalala.mdsuser.entity.vo.Page;
import com.forceslalala.mdsuser.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Author: wangtao
 * @Date: 2024/2/26 15:21
 * @Describe:
 */

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据条件获取用户列表
     */
    @GetMapping("/getList")
    public Page<User> getList(@RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize,
                              @RequestParam(name = "page_num", required = false, defaultValue = "0") int pageNum) {

        PageInfo<User> pageInfo = userService.getUsers(pageNum, pageSize);
        Page<User> result = new Page<>(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPageNum(),
                pageInfo.getPageSize(), pageInfo.getPages());

        return result;
    }
}
