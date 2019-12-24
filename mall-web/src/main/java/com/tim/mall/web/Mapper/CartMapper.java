package com.tim.mall.web.Mapper;

import com.tim.mall.model.User;
import com.tim.mall.web.Model.Cart;
import jdk.nashorn.internal.ir.CatchNode;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface CartMapper extends Mapper<Cart> {

    @Select({"select product_name from cart"})
    List<String> selectAllName();

    @Update({"update cart set quantity=#{cart.quantity},update_time=#{cart.updateTime},total=#{cart.total} where product_name=#{cart.productName} and user_id=#{user.id}"})
    void updateByName(@Param("cart") Cart cart, @Param("user") User user);

    @Select({"select id,product_name,product_id,quantity,product_price,total from Cart where id =#{id}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="product_name",property = "productName",jdbcType = JdbcType.VARCHAR),
            @Result(column="product_id",property = "productId",jdbcType = JdbcType.INTEGER),
            @Result(column="quantity", property="quantity", jdbcType= JdbcType.INTEGER),
            @Result(column="total", property="total", jdbcType= JdbcType.INTEGER),
            @Result(column="product_price", property="productPrice", jdbcType= JdbcType.INTEGER),
    })
    Cart selectById(int id);

    @Delete({"delete from cart where id =#{id}"})
    void deleteById(Integer id);

    @Update({"update cart set quantity=#{quantity},total=#{total} where id =#{id}"})
    void updateQuantityById(@Param("id") Integer id, @Param("quantity") Integer quantity,@Param("total") Integer total);

    @Select({"select id,product_img,product_name,quantity,product_price,total from Cart where user_id =#{id}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="product_img",property = "productImg",jdbcType = JdbcType.VARCHAR),
            @Result(column="product_name",property = "productName",jdbcType = JdbcType.VARCHAR),
            @Result(column="quantity", property="quantity", jdbcType= JdbcType.INTEGER),
            @Result(column="total", property="total", jdbcType= JdbcType.INTEGER),
            @Result(column="product_price", property="productPrice", jdbcType= JdbcType.INTEGER),
    })
    List<Cart> selectAll_currentUser(Integer id);
}
