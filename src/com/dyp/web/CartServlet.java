package com.dyp.web;

import com.dyp.pojo.Book;
import com.dyp.pojo.Cart;
import com.dyp.pojo.CartItem;
import com.dyp.service.BookService;
import com.dyp.service.impl.BookServiceImpl;
import com.dyp.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author howard
 * @version 1.0
 */
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }

        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        cart.addItem(cartItem);
        req.getSession().setAttribute("last",cartItem.getName());
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }

        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        cart.addItem(cartItem);
        req.getSession().setAttribute("last",cartItem.getName());

        Map<String,Object> map = new HashMap<>();
        map.put("last",book.getName());
        map.put("count",cart.getTotalCount());
        resp.getWriter().write(new Gson().toJson(map));
    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.deleteItem(id);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"),0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.updateCount(id,count);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}