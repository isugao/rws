package com.gao.user.controller;


import com.gao.user.common.ApiResult;
import com.gao.user.common.UIdUtil;
import com.gao.user.entity.User;
import com.gao.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("{id}")
    public ApiResult<User> detail(@PathVariable String id) {
        return ApiResult.ok(userService.getById(id));
    }

    @GetMapping("add")
    public ApiResult<Boolean> add() {
        User user = new User();
        user.setId(UIdUtil.getInstance().uid());
        user.setAge(12);
        user.setName("admin");
        return ApiResult.ok(userService.save(user));
    }

}
