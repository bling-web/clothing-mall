package com.tim.mall.admin.Controller;

import com.google.gson.Gson;
import com.tim.mall.admin.common.WebResult;
import com.tim.mall.admin.model.Menu;
import com.tim.mall.model.User;
import com.tim.mall.admin.service.MenuService;
import com.tim.mall.common.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("admin/menu")
public class MenuController {


    @Resource
    private MenuService menuService;
    /**
     * 转到menu页面的方法,该方法没有标注路径,则访问admin/menu即可访问.
     * @return
     */
    @RequestMapping
    public String toMenu(ModelMap map){
        List<Menu> menus = menuService.selectAllMenu();
        //将menus对象转换成json数据格式的对象,因为在freeworker中接收的方法只能接收原始的字符串
        map.addAttribute("menu",new Gson().toJson(menus));
        return "menu";
    }
    /**
     * 增加菜单栏方法
     * @param menu
     * @param session
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public WebResult Edit(Menu menu, HttpSession session){
        User user = (User)session.getAttribute(Context.CURRENT_USER);
        menu.setCreator(user.getId());
        menu.setCreatetime(new Date());
        menu.setFlag("1");
        boolean falg = menuService.add(menu, user);
        if(falg)
            return WebResult.success();
        else
            return WebResult.unknown();
    }

    /**
     * 删除菜单方法
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public WebResult delete(@RequestParam Integer id){

        boolean flag = menuService.delete(id);
        if(flag) return WebResult.success();
        else return WebResult.unknown();

    }
}
