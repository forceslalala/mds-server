package com.forceslalala.mdsuser.entity;

import lombok.Data;

/**
 * @ClassName User
 * @Author: wangtao
 * @Date: 2023/11/24 17:17
 * @Describe:
 */
@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private String salt;

    private String phone;

    private String email;

    private int gender;

    private int isDelete;
}
