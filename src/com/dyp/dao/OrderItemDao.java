package com.dyp.dao;

import com.dyp.pojo.OrderItem;

public interface OrderItemDao {
    /**
     * 保存一条订单项
     * @param orderItem
     * @return 成功返回1，失败返回-1
     */
    public int saveOrderItem(OrderItem orderItem);
}
