package com.dyp.test;

import com.dyp.dao.UserDao;
import com.dyp.dao.impl.UserDaoImpl;
import com.dyp.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.queryUserByUsername("admind"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.queryUserByUsernameAndPassword("admin","admin"));
    }

    @Test
    public void saveUser() {
        UserDao userDao = new UserDaoImpl();
        User u = new User(null,"dyp","123","howard1209a@163.com");
        System.out.println(userDao.saveUser(u));
    }
}