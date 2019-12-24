package com.tim.mall.web.Service.impl;

import com.tim.mall.mapper.UserMapper;
import com.tim.mall.model.User;
import com.tim.mall.web.Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;
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

    @Override
    public void updateLoginTime(Date date, Integer id) {
        userMapper.updateLoginTime(date,id);
    }

    @Override
    public Integer insert(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public boolean selectName(String name) {
        List<String> names = userMapper.selectAllName();
        if(names.contains(name))return true;
        else return false;
    }
}
