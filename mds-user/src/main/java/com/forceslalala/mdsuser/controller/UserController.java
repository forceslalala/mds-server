package com.forceslalala.mdsuser.controller;

import com.forceslalala.mdsuser.entity.User;
import com.forceslalala.mdsuser.entity.vo.Page;
import com.forceslalala.mdsuser.entity.vo.Result;
import com.forceslalala.mdsuser.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;


/**
 * @ClassName UserController
 * @Author: wangtao
 * @Date: 2024/2/26 15:21
 * @Describe:
 */

@RestController
@RequestMapping("/api/v1/user")
@Api(tags = "医院用户接口")
public class UserController {

    /**
     * session的字段名
     */
    public static final String SESSION_NAME = "userInfo";

    @Autowired
    private UserService userService;

    @GetMapping("/getList")
    @ApiOperation(value = "根据条件获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_size", value = "页面数据量", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "page_num", value = "当前页码", required = false, dataType = "int", paramType = "query")
    })
    public Result<Page<User>> getList(
            @RequestParam(name = "page_size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "page_num", required = false, defaultValue = "0") int pageNum) {

        PageInfo<User> pageInfo = userService.getUsers(pageNum, pageSize);
        Page<User> page = new Page<>(pageInfo.getTotal(), pageInfo.getList(), pageInfo.getPageNum(),
                pageInfo.getPageSize(), pageInfo.getPages());

        Result<Page<User>> result = new Result<>();
        result.setData(page);
        result.setSuccess(true);
        return result;
    }

    @PostMapping("/login")
    @ApiOperation(value = "登陆")
    public Result<User> login(@RequestBody @Valid User user, BindingResult errors, HttpServletRequest request) {
        Result<User> result;
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return result;
        }
        // 调用登录服务
        result = userService.login(user);
        // 如果登录成功，则设定session
        if (result.isSuccess()) {
            request.getSession().setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public Result<User> register(@RequestBody @Valid User user, BindingResult errors) {
        Result<User> result;
        // 如果校验有错，返回注册失败以及错误信息
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return result;
        }
        // 调用注册服务
        result = userService.register(user);
        return result;
    }

    @GetMapping("/isLogin")
    @ApiOperation(value = "判断用户是否登陆")
    public Result<User> isLogin(HttpServletRequest request) {
        // 传入session到用户服务层
        return userService.isLogin(request.getSession());
    }

    @GetMapping("/logout")
    @ApiOperation(value = "用户退出登陆")
    public Result<Void> logout(HttpServletRequest request) {
        Result<Void> result = new Result<>();
        // 用户登出很简单，就是把session里面的用户信息设为null即可
        request.getSession().setAttribute(SESSION_NAME, null);
        result.setResultSuccess("用户退出登录成功！");
        return result;
    }
}
