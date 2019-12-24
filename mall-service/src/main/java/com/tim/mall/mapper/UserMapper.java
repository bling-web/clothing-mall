package com.tim.mall.mapper;

import com.tim.mall.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

public interface UserMapper extends Mapper<User> {

    @Select({"select name,email,id,creator,tenantid,flag from User where psw=#{psw} and flag=1"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="tenantid", property="tenantid", jdbcType= JdbcType.DATE),
            @Result(column="email", property="email", jdbcType= JdbcType.VARCHAR),
            @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
            @Result(column="flag", property="flag", jdbcType= JdbcType.INTEGER),
    })
    List<User> dologin(String psw);

    //根据用户的id查询该用户所拥有的权限,也就是有权访问的url.
    @Select({"SELECT url FROM menu WHERE id IN  (SELECT \n" +
            "menuid FROM role_menu WHERE roleid=(SELECT roleid FROM user_role WHERE userid=#{id}))"})
    List<String> selectPermssion(Integer id);



    @Select({"select id,name,tenantid,email,creator,createtime,flag,logintime from user limit #{start},#{length}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="tenantid", property="tenantid", jdbcType= JdbcType.DATE),
            @Result(column="email", property="email", jdbcType= JdbcType.VARCHAR),
            @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
            @Result(column="createtime", property="createtime", jdbcType= JdbcType.DATE),
            @Result(column="flag", property="flag", jdbcType= JdbcType.INTEGER),
            @Result(column="logintime", property="logintime", jdbcType= JdbcType.DATE)
    })
    List<User> selectByAll(@Param("start") Integer start, @Param("length") Integer length);



    @Select({"select id,name,tenantid,email,creator,createtime,flag,logintime from user where name like \"%\""+"#{query} "+"\"%\""+"limit #{start},#{limit}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="tenantid", property="createtime", jdbcType= JdbcType.DATE),
            @Result(column="email", property="email", jdbcType= JdbcType.VARCHAR),
            @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
            @Result(column="createtime", property="createtime", jdbcType= JdbcType.DATE),
            @Result(column="flag", property="flag", jdbcType= JdbcType.INTEGER),
            @Result(column="logintime", property="logintime", jdbcType= JdbcType.DATE)
    })
    List<User> selectByName(@Param("query")String query,@Param("start")int start, @Param("limit")int limit);



    @Select({"select count(id) from user  where name like \"%\""+"#{query}"+"\"%\""})
    Integer selectCountByName(@Param("query")String query);

    /**
     * 更新当前用户登录时间
     * @param date
     * @param id
     */
    @Update({"update user set logintime=#{date} where id =#{id}"})
    void updateLoginTime(@Param("date") Date date,@Param("id") int id);

    @Select({"select id,name,tenantid,email,creator,createtime,flag,logintime from user where id=#{id}"})
    User selectById(int id);

    /**
     * 更新不需要更新密码
     * @param user
     * @return
     */
    @Update({"update user set name=#{user.name},flag=#{user.flag},email=#{user.email} where id =#{user.id}"})
    Integer updateUser(@Param("user") User user);

    @Delete({"delete from user where id=#{id}"})
    void deleteById(Integer id);

    /**
     * 查询该用户所对应的角色
     * @param userid
     * @return
     */
    @Select({"select roleid from user_role where userid=#{userid}"})
    List<Integer> selectUser_RoleById(int userid);

    /**
     * 删除该用户的对应某个角色
     * @param roleid
     * @param userid
     * @param
     * @return
     */
    @Delete({"delete from user_role where roleid=#{roleid} and userid=#{userid}"})
    Integer deleteUser_RoleById(@Param("roleid") Integer roleid, @Param("userid") Integer userid);

    /**
     * 为某个用户增加角色
     * @param userid
     * @param roleid
     * @param creator
     * @return
     */
    @Insert({"INSERT INTO user_role (userid,roleid,creator) VALUES (#{userid},#{roleid},#{creator})"})
    int insertUser_Role(@Param("userid")Integer userid, @Param("roleid")Integer roleid,@Param("creator")Integer creator);

    @Select({"select name from user"})
    List<String> selectAllName();
}
