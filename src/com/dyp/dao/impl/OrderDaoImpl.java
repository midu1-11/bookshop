package com.dyp.dao.impl;

import com.dyp.dao.OrderDao;
import com.dyp.pojo.Order;

/**
 * @author howard
 * @version 1.0
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }
}
