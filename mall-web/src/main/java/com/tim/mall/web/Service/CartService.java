package com.tim.mall.web.Service;

import com.tim.mall.model.User;
import com.tim.mall.web.Model.Cart;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {
    boolean insert(Cart cart, User user);

    List<Cart> selectAll();

    List<String> selectAllName();

    void update(Cart cart, User user);

    Cart selectById(Integer id);

    void deleteById(Integer id);

    void updateQuantityById(Integer id, Integer quantity, Integer total);

    List<Cart> selectAll_currentUser(Integer id);

    void deleteGoodsFromCart(HttpSession session);

    Cart createCart(HttpSession session, String get_id, Cart cart_direct);

    List<Cart> createOrderDate(String[] ids, String[] help_id, String[] quantitys);

    void updateGoodsQuantity(HttpSession session);
}
