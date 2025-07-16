package com.gao.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gao.api.model.User;
import com.gao.api.service.UserSpiService;
import com.gao.business.entity.Order;
import com.gao.business.mapper.OrderMapper;
import com.gao.business.service.OrderService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @DubboReference
    private UserSpiService userSpiService;

    @Override
    public User userInfo(String id) {
        return userSpiService.findById(id);
    }
}
