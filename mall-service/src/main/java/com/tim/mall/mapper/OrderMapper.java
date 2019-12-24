package com.tim.mall.mapper;

import com.tim.mall.model.MyOrder;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface OrderMapper extends Mapper<MyOrder> {

    @Update({"update my_order set status=#{status},payment_time=#{date} where order_no=#{orderNo}"})
    void updateStatus(@Param("orderNo") String orderNo, @Param("status") Integer status, @Param("date") Date date);


    @Select({"select status,payment_time from my_order where order_no=#{orderNo}"})
    @Results({
            @Result(column="status",property = "status",jdbcType = JdbcType.INTEGER),
            @Result(column="payment_time", property="paymentTime", jdbcType= JdbcType.DATE),
    })
    MyOrder selectByOrderNo(String orderNo);

    /**
     * 根据所查询订单号查询订单,默认查询订单号为""
     * @param query
     * @param start
     * @param
     * @return
     */
    @Select({"select id,user_id,order_no,user_id,shipping_id,payment,postage,remark,status,payment_time" +
            " from my_order where order_no like \"%\""+"#{query} "+"\"%\""+" limit #{start},#{limit}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="order_no", property="orderNo", jdbcType= JdbcType.VARCHAR),
            @Result(column="user_id", property="userId", jdbcType= JdbcType.INTEGER),
            @Result(column="shipping_id", property="shippingId", jdbcType= JdbcType.INTEGER),
            @Result(column="payment", property="payment", jdbcType= JdbcType.DOUBLE),
            @Result(column="postage", property="postage", jdbcType= JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType= JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType= JdbcType.INTEGER),
            @Result(column="payment_time", property="paymentTime", jdbcType= JdbcType.DATE),
    })
    List<MyOrder> selectByName(@Param("query")String query, @Param("start")int start, @Param("limit")int limit);


    @Select({"select count(id) from my_order where order_no like \"%\""+"#{query} "+"\"%\""})
    int selectCount_query(String query);


    /**
     * 根据status和搜索内容进行查询
     * @param query
     * @param start
     * @param limit
     * @param status
     * @return
     */
    @Select({"select id,user_id,order_no,user_id,shipping_id,payment,postage,remark,status,payment_time" +
            " from my_order where order_no like \"%\""+"#{query} "+"\"%\""+" and status=#{status} limit #{start},#{limit}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="order_no", property="orderNo", jdbcType= JdbcType.VARCHAR),
            @Result(column="user_id", property="userId", jdbcType= JdbcType.INTEGER),
            @Result(column="shipping_id", property="shippingId", jdbcType= JdbcType.INTEGER),
            @Result(column="payment", property="payment", jdbcType= JdbcType.DOUBLE),
            @Result(column="postage", property="postage", jdbcType= JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType= JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType= JdbcType.INTEGER),
            @Result(column="payment_time", property="paymentTime", jdbcType= JdbcType.DATE),
    })
    List<MyOrder> selectStatusByName(@Param("query")String query, @Param("start")int start, @Param("limit")int limit,@Param("status") int status);


    @Select({"select count(id) from my_order where order_no like \"%\""+"#{query} "+"\"%\""+" and status=#{status}"})
    int selectStatusCount(@Param("query") String query, @Param("status") int status);


    @Select({"select id,user_id,order_no,user_id,shipping_id,payment,postage,remark,status,payment_time" +
            " from my_order where id =#{id}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="order_no", property="orderNo", jdbcType= JdbcType.VARCHAR),
            @Result(column="user_id", property="userId", jdbcType= JdbcType.INTEGER),
            @Result(column="shipping_id", property="shippingId", jdbcType= JdbcType.INTEGER),
            @Result(column="payment", property="payment", jdbcType= JdbcType.DOUBLE),
            @Result(column="postage", property="postage", jdbcType= JdbcType.INTEGER),
            @Result(column="remark", property="remark", jdbcType= JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType= JdbcType.INTEGER),
            @Result(column="payment_time", property="paymentTime", jdbcType= JdbcType.DATE),
            @Result(column="create_time", property="createTime", jdbcType= JdbcType.DATE),
    })
    MyOrder selectById(int id);

    @Update({"update my_order set order_no=#{myOrder.orderNo},user_id=#{myOrder.userId},shipping_id=#{myOrder.shippingId}," +
            "payment=#{myOrder.payment},postage=#{myOrder.postage},remark=#{myOrder.remark},status=#{myOrder.status} where id=#{myOrder.id}"})
    int updateOrder(@Param("myOrder") MyOrder myOrder);

    @Delete({"delete from my_order where id=#{delId}"})
    int deleteById(int delId);
}
