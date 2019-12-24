package com.tim.mall.admin.mapper;

import com.tim.mall.admin.model.Menu;
import com.tim.mall.admin.model.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMapper extends Mapper<Role> {

    @Select({"select id,name,createtime,creator,description from role where name like \"%\""+"#{query} "+"\"%\""+"limit #{start},#{limit}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="createtime", property="createtime", jdbcType= JdbcType.DATE),
            @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
            @Result(column="description", property="description", jdbcType= JdbcType.VARCHAR),
    })
    List<Role> selectByName(@Param("query")String query,@Param("start")int start, @Param("limit")int limit);



    @Select({"select id,name,createtime,creator,description from role limit #{start},#{limit}"})
    @Results({
            @Result(column="id",property = "id",jdbcType = JdbcType.INTEGER,id=true),
            @Result(column="name", property="name", jdbcType= JdbcType.VARCHAR),
            @Result(column="createtime", property="createtime", jdbcType= JdbcType.DATE),
            @Result(column="creator", property="creator", jdbcType= JdbcType.INTEGER),
            @Result(column="description", property="description", jdbcType= JdbcType.VARCHAR),
    })
    List<Role> selectByAll(@Param("start") Integer start, @Param("limit") Integer limit);



    @Select({"select id from role"})
    List<Integer> selectAllId();

    @Select({"select id,name,createtime,creator,description from role where id =#{id}"})
    Role selectById(int id);

    @Update({"update role set name=#{role.name},description=#{role.description} where id =#{role.id}"})
    int updateRole(@Param("role") Role role);

    @Delete("delete from role where id= #{id}")
    void deleteById(int id);

    /**
     * 根据左外连接,查询出所有menu和其对应的roleid,显示在一张表上,如果没有该角色没有该菜单的权限,则显示为null
     * @param id
     * @return
     */
    @Select({"SELECT m.id,m.name,m.url,m.icon,m.menutype,m.display,m.parentid ,r.roleid FROM menu m LEFT JOIN  (SELECT roleid,menuid FROM role_menu WHERE roleid=#{id}) r ON m.id=r.menuid"})
    List<Menu> selectMenu_RoleId(int id);

    @Select({"select menuid from role_menu where roleid=#{roleid}"
    })
    List<Integer> selectRole_MenuById(int roleid);

    @Delete({"DELETE from role_menu where menuid=#{menuid} and roleid=#{roleid}"})
    Integer deleteRole_MenuById(@Param("menuid") Integer menuid,@Param("roleid") Integer roleid);

    @Insert({"insert into role_menu (roleid,menuid,flag,creator) values (#{roleid},#{addMenuid},1,#{roleid})"})
    Integer insertRole_MenuId(@Param("addMenuid") Integer addMenuid,@Param("roleid") Integer roleid);


    @Select({"select count(id) from role  where name like \"%\""+"#{query}"+"\"%\""})
    Integer selectCountByName(@Param("query")String query);

    /**
     * 根据左外连接查询所有的role,并且将userid加到同一张表上.
     * @param id
     * @return
     */
    @Select({"SELECT r.id,r.name,r.createtime,r.creator,r.description,u.userid FROM role r LEFT JOIN (SELECT * FROM user_role WHERE userid =#{id}) u ON u.roleid=r.id"})
    List<Role> selectUser_RoleById(int id);
}
