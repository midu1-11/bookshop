package com.dyp.service;

import com.dyp.pojo.User;

public interface UserService {
    /**
     * 用户注册
     * @param user
     */
    public void registerUser(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 用户名是否存在
     * @param username
     * @return
     */
    public boolean existsUsername(String username);
}
