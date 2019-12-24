package com.tim.mall.admin.service;


import com.tim.mall.admin.common.WebResult;
import com.tim.mall.admin.model.Role;
import com.tim.mall.model.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    void delete(Integer id) ;

    User dologin(String name, String psw);

    List<String> selectPermission(Integer id);

    WebResult updatePasswode(String old_psw, String psw, User user);

    List<User> selectByAll(int start, int length);

    List<User> selectByName(String query, int start, int limit);

    Integer selectCountByName(String query);

    void updateLoginTime(Date date,int id);

    User selectById(int id);

    boolean updateUser(User user);

    boolean insertUser(User user);

    List<Role> selectUser_RoleId(int id);

    boolean updateUser_Role(String[] id, int userid, User current_user);
}
