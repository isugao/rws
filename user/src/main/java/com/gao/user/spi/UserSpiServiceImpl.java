package com.gao.user.spi;

import com.gao.api.service.UserSpiService;
import com.gao.user.entity.User;
import com.gao.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class UserSpiServiceImpl implements UserSpiService {
    @Resource
    private UserService userService;

    @Override
    public com.gao.api.model.User findById(String id) {
        User user = userService.getById(id);
        com.gao.api.model.User user1 = new com.gao.api.model.User();
        user1.setId(user.getId());
        user1.setName(user.getName());
        user1.setAge(user.getAge());
        return user1;
    }
}
