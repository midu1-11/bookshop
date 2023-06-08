package com.dyp.pojo;

import com.sun.corba.se.impl.oa.toa.TOA;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author howard
 * @version 1.0
 */
public class Cart {
    private Map<Integer, CartItem> items = new HashMap<>();


    /**
     * 添加商品项
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        if (items.containsKey(cartItem.getId())) {
            CartItem ci = items.get(cartItem.getId());
            ci.setCount(ci.getCount() + 1);
            ci.setTotalPrice(ci.getTotalPrice().add(ci.getPrice()));
        } else {
            items.put(cartItem.getId(), cartItem);
        }
    }

    /**
     * 删除商品项
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 更新某个商品项的商品数
     *
     * @param id
     * @param count
     */
    public void updateCount(Integer id, Integer count) {
        CartItem ci = items.get(id);
        if (ci != null) {
            ci.setCount(count);
            ci.setTotalPrice(ci.getPrice().multiply(new BigDecimal(ci.getCount())));
        }
    }


    public Integer getTotalCount() {
        Integer totalCount = 0;
        Set<Map.Entry<Integer, CartItem>> entrySet = items.entrySet();
        for (Map.Entry<Integer, CartItem> entry : entrySet) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        Set<Map.Entry<Integer, CartItem>> entrySet = items.entrySet();
        for (Map.Entry<Integer, CartItem> entry : entrySet) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", list=" + items +
                '}';
    }
}
