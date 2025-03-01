package com.chuwa.learn.account_service.service;

import com.chuwa.learn.account_service.model.User;


public interface AuthService {
    User register(String email, String username, String password);

    String login(String loginName, String rawPassword);

}
