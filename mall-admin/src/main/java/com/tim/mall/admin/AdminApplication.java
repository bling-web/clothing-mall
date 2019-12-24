package com.tim.mall.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;


@SpringBootApplication
@ComponentScan("com.tim.mall")
@MapperScan({"com.tim.mall.admin.mapper","com.tim.mall.mapper"})
@EnableTransactionManagement
public class AdminApplication {

    public static void main(String[] args) {

        SpringApplication.run(AdminApplication.class,args);

    }


}
