package com.dyp.service;

import com.dyp.pojo.Cart;

public interface OrderService {
    /**
     * 根据当前购物车信息生成订单和订单项，然后清空购物车，更新商品库存和销量
     * @param cart
     * @param userId
     */
    public String createOrder(Cart cart,Integer userId);
}
