package com.dyp.dao;

import com.dyp.pojo.User;

public interface UserDao {



    /**
     * 根据用户名查询用户信息
     * @param username
     * @return 查到这个用户名则返回User对象，查不到则返回null
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null则说明用户名或密码错误
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return 成功则返回1，失败则返回-1
     */
    public int saveUser(User user);
}
