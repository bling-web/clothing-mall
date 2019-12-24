package com.tim.mall.web.Mapper;

import com.tim.mall.web.Model.Shipping;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

public interface ShippingMapper extends Mapper<Shipping> {

    @Select({"select name,phone,detail_address FROM shipping where user_id=#{id} limit 1"})
    @Results({
            @Result(column="name",property = "name",jdbcType = JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType= JdbcType.VARCHAR),
            @Result(column="detail_address", property="detailAddress", jdbcType= JdbcType.VARCHAR),
    })
    Shipping selectByUser(Integer id);
}
