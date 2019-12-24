package com.tim.mall.admin;

import com.tim.mall.mapper.UserMapper;
import com.tim.mall.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class MybatiTest {


    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect(){
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user.getName());
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }

    }
}
