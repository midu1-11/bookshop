package com.dyp.test;

import com.dyp.pojo.Book;
import com.dyp.pojo.Page;
import com.dyp.pojo.User;
import com.dyp.service.UserService;
import com.dyp.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"howard","123456","2862725651@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"howar","123456","2862725651@qq.com")));
    }

    @Test
    public void existsUsername() {
        System.out.println(userService.existsUsername("howar"));;
    }
}