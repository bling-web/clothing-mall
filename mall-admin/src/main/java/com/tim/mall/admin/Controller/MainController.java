package com.tim.mall.admin.Controller;

import com.tim.mall.admin.model.Menu;
import com.tim.mall.model.User;
import com.tim.mall.admin.service.MenuService;
import com.tim.mall.admin.service.UserService;
import com.tim.mall.common.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class MainController {

    @Resource
    private UserService userService;

    @Resource
    private MenuService menuService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }


    /**
     * 进入主页面
     * @return
     */
    @GetMapping("/index")
    public String index(HttpSession session,ModelMap map){
        List<String> url = (List<String>)session.getAttribute(Context.PERMISSION_ATTRIBUTES);
        List<Menu> menus = menuService.selectByUser((User) session.getAttribute(Context.CURRENT_USER));
        map.put("treeMenu",menus);
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam(defaultValue="")String name, @RequestParam(defaultValue="")String psw,
                        ModelMap map, HttpSession session){
        User user = userService.dologin(name, psw);
        if(user!=null){
            //向session中增加当前用户 ,标志其是否登录成功.
            session.setAttribute(Context.CURRENT_USER,user);
            //把当前用户所等访问的url存起来
            session.setAttribute(Context.PERMISSION_ATTRIBUTES,userService.selectPermission(user.getId()));
            //更新用户系统登录时间
            userService.updateLoginTime(new Date(),user.getId());
            return "redirect:/admin/index";
        }
        else{
            map.put("error","用户名或密码错误");
            return "login";
        }
    }

    /**
     * 注销登录
     * @param session
     * @return
     */
    @GetMapping("/loginout")
    public String loginout(HttpSession session){
        session.setAttribute(Context.CURRENT_USER,null);
        session.setAttribute(Context.PERMISSION_ATTRIBUTES,null);
        return "login";

    }


}
