package com.tim.mall.web.Service;

import com.tim.mall.model.User;

import java.lang.reflect.Member;
import java.util.Date;

public interface UserService {
    User dologin(String name, String psw);

    void updateLoginTime(Date date, Integer id);

    Integer insert(User user);

    boolean selectName(String name);
}
