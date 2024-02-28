package com.forceslalala.mdsuser.service;

import com.forceslalala.mdsuser.entity.User;
import com.forceslalala.mdsuser.entity.vo.Result;
import com.github.pagehelper.PageInfo;
import javax.servlet.http.HttpSession;


/**
 * @ClassName UserService
 * @Author: wangtao
 * @Date: 2024/2/26 16:22
 * @Describe:
 */

public interface UserService {

    /**
     * 根据条件获取用户列表
     */
    PageInfo<User> getUsers(int pageNum, int pageSize);

    /**
     * 用户登陆
     */
    Result<User> login(User user);

    /**
     * 用户注册
     */
    Result<User> register(User user);

    /**
     * 判断用户是否登录（实际上就是从session取出用户id去数据库查询并比对）
     */
    Result<User> isLogin(HttpSession session);

}
