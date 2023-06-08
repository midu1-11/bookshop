package com.dyp.service.impl;

import com.dyp.dao.BookDao;
import com.dyp.dao.OrderDao;
import com.dyp.dao.OrderItemDao;
import com.dyp.dao.impl.BookDaoImpl;
import com.dyp.dao.impl.OrderDaoImpl;
import com.dyp.dao.impl.OrderItemDaoImpl;
import com.dyp.pojo.*;
import com.dyp.service.OrderService;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author howard
 * @version 1.0
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderId = System.currentTimeMillis() + "" + userId;
        orderDao.saveOrder(new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId));
        for (CartItem cartItem : cart.getItems().values()) {
            orderItemDao.saveOrderItem(new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId));
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clear();
        return orderId;
    }
}
