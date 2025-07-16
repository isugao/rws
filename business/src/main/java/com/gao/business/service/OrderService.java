package com.gao.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.api.model.User;
import com.gao.business.entity.Order;

public interface OrderService extends IService<Order> {
    User userInfo(String id);
}
