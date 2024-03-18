package com.forceslalala.mdsuser.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "用户名不能为空！")
    private String username;

    @NotEmpty(message = "密码不能为空！")
    @Size(min = 8, message = "密码长度不能小于8！")
    private String password;

    private String salt;

    private String phone;

    private String email;

    /**
     * 0-女
     * 1-男
     */
    private int gender;
}
