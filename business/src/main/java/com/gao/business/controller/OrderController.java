package com.gao.business.controller;

import com.gao.api.model.User;
import com.gao.business.common.ApiResult;
import com.gao.business.common.UIdUtil;
import com.gao.business.entity.Order;
import com.gao.business.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("{id}")
    public ApiResult<Order> detail(@PathVariable String id) {
        return ApiResult.ok(orderService.getById(id));
    }

    @GetMapping("user/info/{id}")
    public ApiResult<User> userInfo(@PathVariable String id) {
        return ApiResult.ok(orderService.userInfo(id));
    }

    @GetMapping("add")
    public ApiResult<Boolean> add() {
        Order order = new Order();
        order.setId(UIdUtil.getInstance().uid());
        order.setRemark("test");
        return ApiResult.ok(orderService.save(order));
    }

}
