package com.tim.mall.admin.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tim.mall.admin.common.WebResult;
import com.tim.mall.admin.model.Role;
import com.tim.mall.model.User;
import com.tim.mall.admin.service.UserService;
import com.tim.mall.common.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("admin/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/updatepass")
    public String ToUpdatePsw(){
        return "user/password";
    }


    /**
     * 更新密码,这是一个Akax请求
     * @param old
     * @param psw
     * @param session
     * @return
     */
    @PostMapping(value="updatepass")
    @ResponseBody
    public WebResult updatePass(String psw,String old, HttpSession session){
        User user = (User) session.getAttribute(Context.CURRENT_USER);
        return userService.updatePasswode(old,psw,user);
    }


    /**
     * 跳转到用户管理页面
     * @return
     */
    @RequestMapping
    public String ToUser(@RequestParam(defaultValue = "10")int pageSize, ModelMap map,
                             @RequestParam(defaultValue = "")String query, @RequestParam(defaultValue = "1") int page ){
        List<User> users;
        //查询全部
        if(query.equals("")){
            users=userService.selectByAll((page-1)*pageSize,pageSize);
            //根据查询内容查
        }else
            users= userService.selectByName(query,(page-1)*pageSize,pageSize);
        //查询总长度
        int count=userService.selectCountByName(query);
        map.addAttribute("users",users);
        map.addAttribute("count",count);
        int maxPage=count/10==0 ? count/10 :(count/10)+1;
        map.addAttribute("maxPage",maxPage);
        map.addAttribute("page",page);
        map.addAttribute("pageSize",pageSize);
        //把查询内容也要传回去
        map.addAttribute("query",query);
        //查询的当前页面的数据在整个页面的位置(排序)
        map.addAttribute("nowBegin",pageSize*(page-1)+1);
        map.addAttribute("nowEnd" ,pageSize*(page-1)+users.size());
        return "user/index";
    }

    /**
     * 跳转到编辑页面
     * @param id
     * @param map
     * @return
     */
    @GetMapping("edit")
    public String toEdit(@RequestParam(defaultValue = "-2") int id,ModelMap map){
        if(id!=-2){
            User user = userService.selectById(id);
            map.addAttribute("user",user);
        }
        return "user/edit";
    }

    /**
     * 更新用户方法
     * @param user
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public WebResult edit(User user,HttpSession session){
        boolean flag;
        if(user.getId()!=null){
            //说明不是新增用户,直接更新
            flag= userService.updateUser(user);
            return returnResult(flag);
        }else{
            User current_user = (User) session.getAttribute("current_user");
            user.setCreator(current_user.getId());
            user.setTenantid(current_user.getId());
            flag = userService.insertUser(user);
            return returnResult(flag);
        }
    }

    /**
     * 删除角色方法
     * @param id
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public WebResult delete(@RequestParam String id){
        //去掉最后一位的逗号
        String realId = id.substring(0, id.length() - 1);
        String[] ids = realId.split(",");
        for (String delId : ids) {
            userService.delete(Integer.valueOf(delId));
        }
        return WebResult.success();
    }

    /**
     * 转到user/menu页面
     * @param id
     * @param map
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("role")
    public String toRole(@RequestParam int id, ModelMap map) {
        List<Role> Roles = userService.selectUser_RoleId(id);
        map.put("userid",(Integer)id);
        map.put("roles",Roles);
//        map.put("treeMenu", users);
        return "/user/role";
    }


    /**
     * 更新角色权限方法
     * @param ids
     * @param userid
     * @return
     */
    @PostMapping("role")
    public WebResult updateUser_Role(@RequestParam String ids,@RequestParam int userid,HttpSession session){
        //去掉最后一位的逗号
        String realId = ids.substring(0, ids.length() - 1);
        String[] id = realId.split(",");
        boolean flag = userService.updateUser_Role(id, userid,(User) session.getAttribute("current_user"));
        if(flag) return  WebResult.success();
        else return WebResult.unknown();
    }

    public WebResult returnResult(boolean flag){
        if(flag) return WebResult.success();
        else return WebResult.unknown();
    }
}
