package com.tim.mall.admin.common;

import com.tim.mall.admin.model.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecursiveFile {

    public static List<Menu> buildRecursive(List<Menu> temp){
        HashMap<Integer, List<Menu>> map = new HashMap<>();
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
}
