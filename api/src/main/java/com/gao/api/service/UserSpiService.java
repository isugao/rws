package com.gao.api.service;

import com.gao.api.model.User;

public interface UserSpiService {
    User findById(String id);
}
