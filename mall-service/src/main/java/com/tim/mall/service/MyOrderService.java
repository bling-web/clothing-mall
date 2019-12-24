package com.tim.mall.service;

import com.tim.mall.model.User;
import com.tim.mall.model.MyOrder;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public interface MyOrderService {
    Integer insert(MyOrder myOrder);

    void updateStutes(String orderNo, Integer status, Date date);

    boolean ifUpdated(String orderNo);

    String createOrderNo(User current_user,int count);

    MyOrder setOrder(HttpSession session, String orderNo, Integer id, Integer id1, String remark);

    List<MyOrder> selectByName(String query, int start, int limit);

    int selectCount_query(String query);

    List<MyOrder> selectStatusByName(String query, int start, int limit, int status);

    int selectStatusCount(String query, int status);

    MyOrder selectById(int id);

    void delete(int id);

    boolean updateOrder(MyOrder order);
}
