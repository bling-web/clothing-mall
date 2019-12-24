package com.tim.mall.admin;


import com.tim.mall.admin.mapper.MenuMapper;
import com.tim.mall.admin.service.MenuService;
import com.tim.mall.admin.service.impl.MenuServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteMenuTest {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private MenuServiceImpl menuService;

    @Test
    public void test(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        List<Integer> allId = menuService.findAllId(3, list);
        for (Integer integer : allId) {
            System.out.println(integer);
        }

    }
}
