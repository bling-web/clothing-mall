package com.tim.mall.web.Service;

import com.tim.mall.web.Model.OrderItem;

import javax.servlet.http.HttpSession;

public interface OrderItemService {
    void insert(OrderItem orderItem);

    void insertOrder_item(HttpSession session, String orderNo);
}
