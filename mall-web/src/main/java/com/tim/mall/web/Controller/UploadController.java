package com.tim.mall.web.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("upload")
public class UploadController {

    @Value("${file-server}")
    private String file_Server;

    @GetMapping
    public String toUploads(){
        return "upload";
    }


    @PostMapping("file-upload")
    public String uploads(){
        //定义一个远程服务器存储空间.在application.yaml文件中.
        //用value注解注入进来


        //获取源文件名称,主要是后面的文件类型


        //重新定义文件名称,加上一个UUID.

        //创建客户端对象

        //客户端与文件服务器进行相连

        //文件上传

        return "";

    }
}
