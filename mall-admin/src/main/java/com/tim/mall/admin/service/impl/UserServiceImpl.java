package com.tim.mall.admin.service.impl;

import com.tim.mall.admin.common.WebResult;
import com.tim.mall.admin.mapper.MenuMapper;
import com.tim.mall.admin.mapper.RoleMapper;
import com.tim.mall.admin.model.Role;
import com.tim.mall.mapper.UserMapper;
import com.tim.mall.model.User;
import com.tim.mall.admin.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMapper roleMapper;


    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @Override
    public void delete(Integer id) {
        userMapper.deleteById(id);

    }

    /**
     * 判断是否可登录
     * @param name
     * @param psw
     * @return
     */
    @Override
    public User dologin(String name, String psw) {
        List<User> userList = userMapper.dologin(psw);
        if(userList.size()==0)
            return null;
        else{
            for (User user : userList) {
                if(user.getName().equals(name)||user.getEmail().equals(name))
                    return user;
            }
        }
        return null;
    }
    /**
     * 查询该用户所拥有的权限
     * @param id
     * @return
     */
    @Override
    public List<String> selectPermission(Integer id) {
        if(id==-1) return menuMapper.selectAllUrl();
        else return userMapper.selectPermssion(id);
    }

    /**
     * 修改密码
     * @param old_psw
     * @param psw
     * @param user
     */
    @Override
    public WebResult updatePasswode(String old_psw, String psw, User user) {
        User help = userMapper.selectByPrimaryKey(user.getId());
        if(help.getPsw().equals(old_psw)){
            help.setUpdateuser(user.getId());
            help.setUpdatetime(new Date());
            help.setPsw(psw);
            //该方法是根据主键更新不为null的值,会返回更新的条数
            if(userMapper.updateByPrimaryKeySelective(help)>0){
                return WebResult.success();
            }else{
                return WebResult.unknown();
            }
        }else{
            return WebResult.error("旧密码错误");
        }

    }

    /**
     * 查询所有的用户
     * @param start
     * @param length
     * @return
     */
    @Override
    public List<User> selectByAll(int start, int length) {
        return userMapper.selectByAll( start, length);
    }

    /**
     * 查询搜索用户
     * @param query
     * @param start
     * @param limit
     * @return
     */
    @Override
    public List<User> selectByName(String query, int start, int limit) {
        return userMapper.selectByName(query,start,limit);
    }

    @Override
    public Integer selectCountByName(String query) {
        return userMapper.selectCountByName(query);
    }

    @Override
    public void updateLoginTime(Date date,int id) {
        userMapper.updateLoginTime(date,id);
    }

    /**
     * 根据id查询该用户
     * @param id
     * @return
     */
    @Override
    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    /**
     * 更加id更新某个用户的属性
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {
        Integer count = userMapper.updateUser(user);
        if(count>0) return true;
        else return false;
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Override
    public boolean insertUser(User user) {
        int count = userMapper.insert(user);
        if(count>0) return true;
        else return false;
    }
    /**
     * 根据左外连接查询所有的role,并且将对应的userid连接到一张表上
     * @param id
     * @return
     */
    @Override
    public List<Role> selectUser_RoleId(int id) {
        return roleMapper.selectUser_RoleById(id);
    }

    /**
     * 需要事务控制
     * 更新该用户所对应的角色,一个用户可以对应多个角色
     * @param return_role_id
     * @param userid
     * @param current_user
     * @return
     */
    @Override
    public boolean updateUser_Role(String[] return_role_id, int userid, User current_user) {
        List<Integer> roleid = userMapper.selectUser_RoleById(userid);
        ArrayList<Integer> temp = new ArrayList<>();
        //这两个参数是判断是否操作数据库成功
        int count=0,num=0;
        //该用户需要删除的角色功能
        for (Integer del_id : roleid) {
            if(!containid(del_id,return_role_id)){
                temp.add(del_id);
            }
        }
        //进行删除
        for (Integer del_role_id : temp) {
            count += userMapper.deleteUser_RoleById(del_role_id,userid);
        }
        num=temp.size();
        temp.clear();
        int help;
        //该角色需要增加的功能
        for (String addid : return_role_id) {
            help=Integer.valueOf(addid);
            if(!roleid.contains(help)){
                temp.add(help);
            }
        }
        //进行增加.
        for (Integer addMenuid : temp) {
            count += userMapper.insertUser_Role(addMenuid,userid,current_user.getId());
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
