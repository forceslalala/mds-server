package com.forceslalala.mdsuser.dao;

import com.forceslalala.mdsuser.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName UserDao
 * @Author: wangtao
 * @Date: 2023/11/24 17:16
 * @Describe:
 */

@Mapper
public interface UserDao {

    /**
     * 获取用户列表
     * @return
     */
    List<User> getUserList();

    /**
     * 根据用户名获取用户
     * @return
     */
    User getUserByUsername(@Param("username") String username);
}
