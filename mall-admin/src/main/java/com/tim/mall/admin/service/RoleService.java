package com.tim.mall.admin.service;

import com.tim.mall.admin.model.Menu;
import com.tim.mall.admin.model.Role;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface RoleService {
    List<Role> selectByName(String query,Integer start, Integer limit);

    List<Role> selectByAll(Integer start, Integer limit);

    int selectCount(String query);

    List<Role> selectAll();

    List<Integer> selectAllId();

    Role selectById(int id);

    boolean updateRole(Role role);

    boolean insertRole(Role role);

    void delete(int id);

    List<Menu> selectMenu_RoleId(int id);

    boolean updateRole_Menu(String[] id, int roleid);
}
