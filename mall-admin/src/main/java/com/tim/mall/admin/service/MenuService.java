package com.tim.mall.admin.service;

import com.tim.mall.admin.model.Menu;
import com.tim.mall.model.User;

import java.util.List;

public interface MenuService {


     boolean delete(Integer id);

    List<Menu> selectByUser(User user);

    List<Menu> selectAllMenu();

    boolean add(Menu menu,User user);
}
