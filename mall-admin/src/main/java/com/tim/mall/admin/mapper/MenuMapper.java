package com.tim.mall.admin.mapper;

import com.tim.mall.admin.model.Menu;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuMapper extends Mapper<Menu> {

    @Select({"SELECT url,NAME,id,flag,parentid,icon FROM menu WHERE id IN  (SELECT \n" +
            "         menuid FROM role_menu WHERE roleid=(SELECT roleid FROM user_role WHERE userid=#{id})) AND menutype!='2'"})
    @Results({
            @Result(column = "id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="url", property="url", jdbcType= JdbcType.VARCHAR),
            @Result(column="flag", property="flag", jdbcType= JdbcType.CHAR),
            @Result(column="parentid", property="parentid", jdbcType= JdbcType.INTEGER),
            @Result(column="icon", property="icon", jdbcType= JdbcType.VARCHAR)
    })
    List<Menu> selectByUser(Integer id);


    @Select({"SELECT url,NAME,id,flag,parentid,icon FROM menu where  menutype!='2'"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="url", property="url", jdbcType= JdbcType.VARCHAR),
            @Result(column="flag", property="flag", jdbcType= JdbcType.CHAR),
            @Result(column="parentid", property="parentid", jdbcType= JdbcType.INTEGER),
            @Result(column="icon", property="icon", jdbcType= JdbcType.VARCHAR)
    })
    List<Menu> selectAllMenu();

    @Select({"SELECT url,NAME,id,flag,parentid,icon FROM menu "})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="url", property="url", jdbcType= JdbcType.VARCHAR),
            @Result(column="flag", property="flag", jdbcType= JdbcType.CHAR),
            @Result(column="parentid", property="parentid", jdbcType= JdbcType.INTEGER),
            @Result(column="icon", property="icon", jdbcType= JdbcType.VARCHAR)
    })
    List<Menu> selfSelectAll();

    @Select({"select id from menu where name=#{name}"})
    Integer selectByName(String name);

    @Select({"select id from menu where parentid=#{id}"})
    List<Integer> selectById(Integer id);

    @Select({"select url from menu"})
    List<String> selectAllUrl();


}
