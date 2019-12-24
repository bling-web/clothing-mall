package com.tim.mall.service.impl;

import com.tim.mall.model.User;
import com.tim.mall.mapper.OrderMapper;
import com.tim.mall.model.MyOrder;
import com.tim.mall.service.MyOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MyOrderServiceImpl implements MyOrderService {

    @Resource
    private OrderMapper orderMapper;
    @Override
    public Integer insert(MyOrder myOrder) {
        return orderMapper.insertSelective(myOrder);
    }

    @Override
    public void updateStutes(String orderNo, Integer status, Date date) {
        orderMapper.updateStatus(orderNo,status,date);
    }

    @Override
    public boolean ifUpdated(String orderNo) {
        MyOrder myOrder = orderMapper.selectByOrderNo(orderNo);
        Date paymentTime = myOrder.getPaymentTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(paymentTime);
        if(myOrder.getStatus()==20&&!date.equals("0000-00-00")) return true;
        else return false;
    }

    /**
     * 创建订单编号
     * @param current_user
     * @return
     */
    @Override
    public String createOrderNo(User current_user,int count) {
        String first="";
        String second="";
        String third="";
        //第一部分,长度为三的userId的标识
        Integer userId = current_user.getId();
        if(userId<10) first="00"+userId;
        else if(userId<99) first="0"+userId;
        else first=String.valueOf(userId);
        //第二部分,长度为12的时间标识
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss");
        second=formatDate.format(new Date());
        //第三部分,长度为5的,第几个订单的标识
        count++;
        int bigit_0=5-String.valueOf(count).length();
        String help="";
        for (int i = 0; i < bigit_0; i++) {
            help += "0";
        }
        third=help+String.valueOf(count);
        return first+second+third;
    }

    @Override
    public MyOrder setOrder(HttpSession session, String orderNo, Integer UserId, Integer shippingId, String remark) {
        MyOrder myOrder = new MyOrder();
        myOrder.setOrderNo(orderNo);
        myOrder.setUserId(UserId);
        myOrder.setShippingId(shippingId);
        myOrder.setPayment((Double) session.getAttribute("sum"));
        myOrder.setPostage(0);
        myOrder.setRemark(remark);
        myOrder.setStatus(10);
        myOrder.setCreateTime(new Date());
        return myOrder;
    }

    @Override
    public List<MyOrder> selectByName(String query, int start, int limit) {
        return orderMapper.selectByName(query,start,limit);
    }

    @Override
    public int selectCount_query(String query) {
        return orderMapper.selectCount_query(query);
    }

    @Override
    public List<MyOrder> selectStatusByName(String query, int start, int limit, int status) {
        return orderMapper.selectStatusByName(query,start,limit,status);
    }

    @Override
    public int selectStatusCount(String query, int status) {
        return orderMapper.selectStatusCount(query,status);
    }

    @Override
    public MyOrder selectById(int id) {
        return orderMapper.selectById(id);
    }

    @Override
    public void delete(int delId) {
        orderMapper.deleteById(delId);
    }

    @Override
    public boolean updateOrder(MyOrder myOrder) {
        return orderMapper.updateOrder(myOrder)>0 ? true : false;
    }
}
