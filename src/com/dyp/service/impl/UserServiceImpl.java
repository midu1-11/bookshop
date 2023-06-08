package com.dyp.service.impl;

import com.dyp.dao.UserDao;
import com.dyp.dao.impl.UserDaoImpl;
import com.dyp.pojo.User;
import com.dyp.service.UserService;

/**
 * @author howard
 * @version 1.0
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username)==null) {
            return false;
        } else {
            return true;
        }
    }
}
