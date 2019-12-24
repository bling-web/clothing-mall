package com.tim.mall.admin.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.tim.mall.admin.common.WebResult;
import com.tim.mall.admin.model.Menu;
import com.tim.mall.admin.model.Role;
import com.tim.mall.admin.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/admin/role")
@Controller
public class RoleController {

    @Resource
    private RoleService roleService;
    /**
     * 进入角色管理方法
     * 在页面中每一次搜索,每一次选页面的显示条数都会重新提交表单
     * @return
     */
    @RequestMapping
    public String ToRoleMenu(@RequestParam(defaultValue = "10")int pageSize, ModelMap map,
                             @RequestParam(defaultValue = "")String query, @RequestParam(defaultValue = "1") int page ){
        List<Role> roles;
        //查询全部
        if(query.equals("")){
            roles=roleService.selectByAll((page-1)*pageSize,pageSize);
        //根据查询内容查
        }else
            roles= roleService.selectByName(query,(page-1)*pageSize,pageSize);
        //查询总长度
        int count=roleService.selectCount(query);
        map.addAttribute("roles",roles);
        map.addAttribute("count",count);
        int maxPage=count/10==0 ? count/10 :(count/10)+1;
        map.addAttribute("maxPage",maxPage);
        map.addAttribute("page",page);
        map.addAttribute("pageSize",pageSize);
        map.addAttribute("query",query);
        //查询的当前页面的数据在整个页面的位置(排序)
        map.addAttribute("nowBegin",pageSize*(page-1)+1);
        map.addAttribute("nowEnd" ,pageSize*(page-1)+roles.size());
        return "role/index";
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
            Role role = roleService.selectById(id);
            map.addAttribute("role",role);
        }
        return "role/edit";
    }

    /**
     * 更新角色方法
     * @param id
     * @param name
     * @param description
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public WebResult edit(@RequestParam(defaultValue = "-2") int id,@RequestParam String name,@RequestParam String description){
        if(id!=-2){
            //更新方法
            Role role = new Role();
            role.setId(id);
            role.setName(name);
            role.setDescription(description);
            boolean flag = roleService.updateRole(role);
            return returnResult(flag);
        }else{
            //新增方法
            Role role = new Role();
            role.setName(name);
            role.setDescription(description);
            boolean flag = roleService.insertRole(role);
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
            roleService.delete(Integer.valueOf(delId));
        }
        return WebResult.success();
    }

    /**
     * 转到role/menu页面
     * @param id
     * @param map
     * @return
     * @throws JsonProcessingException
     */
    @GetMapping("menu")
    public String toMenu(@RequestParam int id, ModelMap map)throws JsonProcessingException {
        List<Menu> menus = roleService.selectMenu_RoleId(id);
        map.put("roleid",(Integer)id);
        //主要这里要将对象转换成json数据
        map.put("menus",new Gson().toJson(menus));
        map.put("treeMenu", menus);
        return "/role/menu";
    }

    /**
     * 更新角色权限方法
     * @param ids
     * @param roleid
     * @return
     */
    @PostMapping("menu")
    @ResponseBody
    public WebResult updateRole_Menu(@RequestParam String ids,@RequestParam int roleid){
        //去掉最后一位的逗号
        String realId = ids.substring(0, ids.length() - 1);
        String[] id = realId.split(",");
        boolean flag = roleService.updateRole_Menu(id, roleid);
        if(flag) return  WebResult.success();
        else return WebResult.unknown();
    }

    public WebResult returnResult(boolean flag){
        if(flag) return WebResult.success();
        else return WebResult.unknown();
    }
}
