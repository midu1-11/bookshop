package com.dyp.web;

import com.dyp.pojo.User;
import com.dyp.service.UserService;
import com.dyp.service.impl.UserServiceImpl;
import com.dyp.utils.WebUtils;
import com.google.gson.Gson;
import com.sun.org.apache.regexp.internal.RE;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author howard
 * @version 1.0
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService = new UserServiceImpl();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.login(new User(null, username, password, null));
        if (user == null) {
            System.out.println("登陆失败！");
            req.setAttribute("msg", "账号或密码错误！");
            req.setAttribute("username", username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            System.out.println("登录成功！");
            req.getSession().setAttribute("username",username);
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userService = new UserServiceImpl();

        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        User user = (User) WebUtils.copyParamToBean(req.getParameterMap(), new User());
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        if (token!=null && token.equalsIgnoreCase(code)) {
            if (userService.existsUsername(username)) {
                req.setAttribute("username", username);
                req.setAttribute("password", password);
                req.setAttribute("email", email);
                req.setAttribute("msg", "用户名重复！");
                System.out.println("用户名重复！");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                System.out.println("注册成功！");
                userService.registerUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("email", email);
            req.setAttribute("msg", "验证码错误！");
            System.out.println("验证码错误！" + code);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existsUsername = userService.existsUsername(username);
        Map<String,Object> map = new HashMap<>();
        map.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }
}
