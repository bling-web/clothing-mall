package com.tim.mall.admin.service.impl;

import com.tim.mall.admin.common.RecursiveFile;
import com.tim.mall.admin.mapper.MenuMapper;
import com.tim.mall.admin.mapper.RoleMenuMapper;
import com.tim.mall.admin.model.Menu;
import com.tim.mall.admin.model.RoleMenu;
import com.tim.mall.model.User;
import com.tim.mall.admin.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    /**
     * 需要事务控制,待测试方法
     * 删除菜单方法
     * @param id
     * @return
     */

    @Override
    public boolean delete(Integer id) {
        ArrayList<Integer> list = new ArrayList<>();
        //找出所有的子id
        List<Integer> allId = findAllId(id, list);
        Menu menu = new Menu();
        RoleMenu roleMenu = new RoleMenu();
        int MenuCount=0,RoleMenuCount=0;
        //挨着进行删除
        for (Integer current_id : allId) {
            menu.setId(current_id);
            MenuCount +=  menuMapper.delete(menu);
            roleMenu.setMenuid(current_id);
            RoleMenuCount += roleMenuMapper.delete(roleMenu);
        }
        if(MenuCount>=allId.size()&&RoleMenuCount>=allId.size()) return true;
        else return false;
    }

    /**
     * 最左边显示菜单的方法,也就是只显示0,1级菜单
     * @param
     * @param user
     * @return
     */
    @Override
    public List<Menu> selectByUser(User user) {
        List<Menu> temp;
        if(user.getId() == -1){
            temp = menuMapper.selectAllMenu();
        }else{
            temp=menuMapper.selectByUser(user.getId());
        }
        return RecursiveFile.buildRecursive(temp);
    }

    /**
     * 对菜单编辑时显示的方法,0,1,2级菜单均显示
     * @return
     */
    @Override
    public List<Menu> selectAllMenu() {
        HashMap<Integer, List<Menu>> map = new HashMap<>();
        List<Menu> temp=menuMapper.selfSelectAll();
        /**
         * 这个循环是把每个子类装到每个对应的父类里面
         */
        for (Menu menu : temp) {
            Integer parentid = menu.getParentid();
            if(map.containsKey(parentid)){
                map.get(parentid).add(menu);
            }
            else{
                ArrayList<Menu> list = new ArrayList<>();
                list.add(menu);
                map.put(parentid,list);
            }

        }
        /**
         * 这个循环是建立层级关系,对每个父类加上对应的childrue,妙用.
         */
        for (Menu menu : temp) {
            if(map.containsKey(menu.getId())){
                menu.setType("folder");
                menu.setChildren(map.get(menu.getId()));
            }else{
                menu.setType("item");
            }
        }
        //只需返回parentid为0的即可
        return map.get(0);
    }

    /**
     *
     * 新增菜单方法
     *    新增的菜单自己可以看到,还有超级管理员可以看到
     * @param menu
     */
    @Override
    public boolean add(Menu menu,User user) {
        //菜单表增加相应菜单
        int menuFlag = menuMapper.insert(menu);
        //判断两个插入是否成功
        if(menuFlag>0){
            return true;
        }else
            return false;
    }

    /**
     * 找出一个菜单id所有的子菜单的方法
     * @return
     */
    public List<Integer> findAllId(Integer id,ArrayList<Integer> list){
        list.add(id);
        //删除一个菜单,其子菜单都需要删除,找出其所有的子菜单
        List<Integer> temp = menuMapper.selectById(id);
        if(temp.isEmpty()) return list;
        for (Integer current_id : temp) {
            list= (ArrayList<Integer>) findAllId(current_id,list);
        }
        return list;
    }


}
