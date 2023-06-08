package com.dyp.dao;

import com.dyp.pojo.Order;

public interface OrderDao {

    /**
     * 保存一条订单
     * @param order
     * @return 成功返回1，失败返回-1
     */
    public int saveOrder(Order order);
}
