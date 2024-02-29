package com.forceslalala.mdsuser.service.impl;

import com.forceslalala.mdsuser.controller.UserController;
import com.forceslalala.mdsuser.dao.UserDao;
import com.forceslalala.mdsuser.entity.User;
import com.forceslalala.mdsuser.entity.vo.Result;
import com.forceslalala.mdsuser.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Author: wangtao
 * @Date: 2024/2/27 13:58
 * @Describe:
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public PageInfo<User> getUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userDao.getUserList();
        return new PageInfo<>(list);
    }

    @Override
    public Result<User> login(User user) {
        Result<User> result = new Result<>();
        User getUser = userDao.getUserByUsername(user.getUsername());
        if (getUser == null) {
            result.setResultFailed("用户不存在！");
            return result;
        }
        // 比对密码
        if (!getUser.getPassword().equals(DigestUtil.md5Hex(user.getPassword() + getUser.getSalt()))) {
            result.setResultFailed("用户名或者密码错误！");
            return result;
        }
        //登陆成功
        result.setResultSuccess("登陆成功", getUser);
        return result;
    }

    @Override
    public Result<User> register(User user) {
        Result<User> result = new Result<>();
        // 先去数据库找用户名是否存在
        User getUser = userDao.getUserByUsername(user.getUsername());
        if (getUser != null) {
            result.setResultFailed("该用户名已存在！");
            return result;
        }
        String salt = this.generateSalt(16);
        user.setSalt(salt);
        user.setPassword(DigestUtil.md5Hex(user.getPassword() + salt));
        // 存入数据库
        if (userDao.add(user) != 0) {
            // 返回成功消息
            result.setResultSuccess("注册用户成功！", user);
        } else {
            result.setResultFailed("注册用户失败！");
        }
        return result;
    }

    // 生成盐值
    private String generateSalt(int length) {
        SecureRandom random = new SecureRandom();
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] saltBytes = new byte[length];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }
}
