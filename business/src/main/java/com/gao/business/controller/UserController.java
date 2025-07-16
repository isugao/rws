package com.gao.business.controller;

import com.gao.business.common.ApiResult;
import com.gao.business.common.UIdUtil;
import com.gao.business.entity.User;
import com.gao.business.service.UserService;
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
