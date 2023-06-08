package com.dyp.web;

import com.dyp.pojo.Cart;
import com.dyp.pojo.User;
import com.dyp.service.OrderService;
import com.dyp.service.impl.OrderServiceImpl;
import com.dyp.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author howard
 * @version 1.0
 */
public class OrderServlet extends BaseServlet {
    OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        Integer userId = user.getId();
        String orderId = null;

        orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");

    }
}
