package com.forceslalala.mdsuser.controller;

import com.forceslalala.mdsuser.entity.User;
import com.forceslalala.mdsuser.entity.vo.Page;
import com.forceslalala.mdsuser.entity.vo.Result;
import com.forceslalala.mdsuser.service.UserService;
import com.forceslalala.mdsuser.utils.TokenUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


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
    public Object login(@RequestBody @Valid User user, BindingResult errors) {
        Result<User> result;
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return result;
        }
        // 调用登录服务
        result = userService.login(user);
        // 如果登录成功，则设定token
        if (result.isSuccess()) {
            String token = TokenUtil.sign(result.getData());
            Map<String, Object> map = new HashMap<>();
            map.put("message", result.getMessage());
            map.put("success", result.isSuccess());
            map.put("token", token);
            return map;
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

}
