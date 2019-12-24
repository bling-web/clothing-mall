package com.tim.mall.admin.service.impl;

import com.tim.mall.admin.common.RecursiveFile;
import com.tim.mall.admin.mapper.RoleMapper;
import com.tim.mall.admin.model.Menu;
import com.tim.mall.admin.model.Role;
import com.tim.mall.admin.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;


    /**
     * 通过名称查询
     * @param query
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<Role> selectByName(String query, Integer start, Integer limit) {
        return roleMapper.selectByName(query,start,limit);
    }

    /**
     * 查询限制范围内的所有
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<Role> selectByAll(Integer start, Integer limit) {
        return roleMapper.selectByAll(start,limit);
    }

    @Override
    public int selectCount(String query) {
        return roleMapper.selectCountByName(query);
    }


    /**
     * 查询所有对象
     * @return
     */
    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    /**
     * 查询所有的id
     * @return
     */
    @Override
    public List<Integer> selectAllId() {
        return roleMapper.selectAllId();
    }


    @Override
    public Role selectById(int id) {
        return roleMapper.selectById(id);
    }

    @Override
    public boolean updateRole(Role role) {
        int flag = roleMapper.updateRole(role);
        if(flag>0)return true;
        else return false;
    }

    @Override
    public boolean insertRole(Role role) {
        int flag = roleMapper.insertSelective(role);
        if(flag>0)return true;
        else return false;
    }

    @Override
    public void delete(int id) {
        roleMapper.deleteById(id);
    }

    /**
     * 根据左外连接,查询所有的menu和每个menu对应的roleid(放在一张表中)
     * @param id
     * @return
     */
    @Override
    public List<Menu> selectMenu_RoleId(int id) {
        List<Menu> temp = roleMapper.selectMenu_RoleId(id);
        for (Menu menu : temp) {
            if(menu.getRoleid()!=0){
                menu.setAdditionalParameters(new HashMap<>());
                menu.getAdditionalParameters().put("item-selected",true);
            }
        }
        //调用构建层级关系的方法
        return RecursiveFile.buildRecursive(temp);
    }

    /**
     * 需要事务控制
     * 更具所选权限,更新该用户所拥有的权限
     * @param id
     * @param roleid
     * @return
     */
    @Override
    public boolean updateRole_Menu(String[] id, int roleid) {
        List<Integer> menuid = roleMapper.selectRole_MenuById(roleid);
        ArrayList<Integer> temp = new ArrayList<>();
        //这两个参数是判断是否操作数据库成功
        int count=0,num=0;
        //该角色需要删除的菜单功能
        for (Integer del_id : menuid) {
            if(!containid(del_id,id)){
                temp.add(del_id);
            }
        }
        //进行删除
        for (Integer deleteid : temp) {
            count += roleMapper.deleteRole_MenuById(deleteid,roleid);
        }
        num=temp.size();
        temp.clear();
        int help;
        //该角色需要增加的功能
        for (String addid : id) {
            help=Integer.valueOf(addid);
            if(!menuid.contains(help)){
                temp.add(help);
            }
        }
        //进行增加.
        for (Integer addMenuid : temp) {
            count += roleMapper.insertRole_MenuId(addMenuid,roleid);
        }
        num += temp.size();
        if(count>=num)return true;
        else return false;
    }

    public boolean containid(int id,String[] arr ){
        for (String s : arr) {
            if(Integer.valueOf(s)==id){
                return true;
            }
        }
        return false;

    }
}
