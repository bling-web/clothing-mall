package com.tim.mall.web.Service.impl;

import com.tim.mall.web.Mapper.OrderItemMapper;
import com.tim.mall.web.Model.Cart;
import com.tim.mall.web.Model.OrderItem;
import com.tim.mall.web.Service.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class OrderItemServcieImpl implements OrderItemService {

    @Resource
    private OrderItemMapper orderItemMapper;
    @Override
    public void insert(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    @Override
    public void insertOrder_item(HttpSession session, String orderNo) {
        List<Cart> checktout_cart = (List<Cart>) session.getAttribute("checktout_cart");
        OrderItem orderItem = new OrderItem();
        for (Cart cart : checktout_cart) {
            orderItem.setName(cart.getProductName());
            orderItem.setQuantity(cart.getQuantity());
            orderItem.setTotalPrice(cart.getTotal());
            orderItem.setOrderNo(orderNo);
            orderItemMapper.insert(orderItem);
        }
    }
}
