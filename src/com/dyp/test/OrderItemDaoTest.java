package com.dyp.test;

import com.dyp.dao.OrderItemDao;
import com.dyp.dao.impl.OrderItemDaoImpl;
import com.dyp.pojo.OrderItem;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem());
    }
}