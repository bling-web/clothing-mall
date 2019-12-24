package com.tim.mall.web.Controller;

import com.tim.mall.model.User;
import com.tim.mall.web.Model.Cart;
import com.tim.mall.web.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class MainController {


    @Resource
    private UserService userService;

    /**
     * 转到登录页面
     * @return
     */
    @GetMapping("sign_in")
    public String toLogin(){
        return "sign_in";
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("sign_out")
    public String toLogin_out(HttpSession session){
        session.removeAttribute("current_user");
        return "sign_in";
    }
    /**
     * 登录方法
     * @param name
     * @param psw
     * @param map
     * @return
     */
    @PostMapping("login")
    public String login(@RequestParam(defaultValue="")String name, @RequestParam(defaultValue="")String psw,
                        ModelMap map, HttpSession session){
        User user = userService.dologin(name, psw);
        if(user!=null){
            //向session中增加当前用户 ,标志其是否登录成功.
            session.setAttribute("current_user",user);
            //判断是否已经到购物车页面
            Cart cart = (Cart) session.getAttribute("cart");
            //如果已经到了进行直接转发
            if(cart!=null){
                return "redirect:/cart";
            }
            //更新用户系统登录时间
            userService.updateLoginTime(new Date(),user.getId());
            return "redirect:/index";
        }
        else{
            map.put("error","用户名或密码错误");
            return "sign_in";
        }
    }



    /**
     * 转到注册页面
     * @return
     */
    @GetMapping("register")
    public String toRegister(){
        return "register";
    }

    /**
     * 对新用户进行注册
     * @param name
     * @param password
     * @param email
     * @param map
     * @param session
     * @return
     */
    @PostMapping("register")
    public String register(String name,String password,String email,ModelMap map,HttpSession session){
        User user = createUser(name, password, email);
        boolean if_contain = userService.selectName(name);
        if(if_contain){
            map.addAttribute("register","该昵称已经被使用,请更换名称");
            return "register";
        }else{
            Integer flag = userService.insert(user);
            if(flag>0){
                //向session中增加当前用户 ,标志其登录成功.
                session.setAttribute("current_user",user);
                return "redirect:/index";
            } else{
                map.addAttribute("register","注册异常,请重新登录");
                return "register";
            }
        }

    }

    private User createUser(String name, String password, String email) {
        User user = new User();
        user.setName(name);
        user.setPsw(password);
        user.setEmail(email);
        user.setCreator(-1);
        user.setCreatetime(new Date());
        user.setFlag(1);
        return user;
    }


}
