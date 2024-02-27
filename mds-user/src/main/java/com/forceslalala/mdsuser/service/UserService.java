package com.forceslalala.mdsuser.service;

import com.forceslalala.mdsuser.dao.UserDao;
import com.forceslalala.mdsuser.entity.User;
import com.forceslalala.mdsuser.entity.vo.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserService
 * @Author: wangtao
 * @Date: 2024/2/26 16:22
 * @Describe:
 */

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据条件获取用户列表
     */
    public PageInfo<User> getUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userDao.getUserList();
        return new PageInfo<>(list);
    }
}
