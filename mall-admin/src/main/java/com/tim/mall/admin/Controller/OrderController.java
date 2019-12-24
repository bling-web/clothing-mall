package com.tim.mall.admin.Controller;

import com.tim.mall.admin.common.WebResult;
import com.tim.mall.model.MyOrder;
import com.tim.mall.service.MyOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("admin/order")
public class OrderController {


    @Resource
    private MyOrderService myOrderService;
    /**
     * 进入角色管理方法
     * 在页面中每一次搜索,每一次选页面的显示条数都会重新提交表单
     * @return
     */
    @RequestMapping
    public String ToGoodsIndex(@RequestParam(defaultValue = "10")int pageSize, ModelMap map, @RequestParam(defaultValue = "-1") int status,
                               @RequestParam(defaultValue = "")String query, @RequestParam(defaultValue = "1") int page ){
        List<MyOrder>  myOrders;
        int count;
        if(status==-1){
            //没有status的查询
            myOrders= myOrderService.selectByName(query,(page-1)*pageSize,pageSize);
            //查询总长度
            count=myOrderService.selectCount_query(query);
        }else{
            //查询不同状态下搜索的内容
            myOrders=myOrderService.selectStatusByName(query,(page-1)*pageSize,pageSize,status);
            count=myOrderService.selectStatusCount(query,status);
        }
        map.addAttribute("status",status);
        map.addAttribute("myOrders",myOrders);
        map.addAttribute("count",count);
        int maxPage=count/10==0 ? count/10 :(count/10)+1;
        map.addAttribute("maxPage",maxPage);
        map.addAttribute("page",page);
        map.addAttribute("pageSize",pageSize);
        map.addAttribute("query",query);
        //查询的当前页面的数据在整个页面的位置(排序)
        map.addAttribute("nowBegin",pageSize*(page-1)+1);
        map.addAttribute("nowEnd" ,pageSize*(page-1)+myOrders.size());
        return "order/index";
    }
    /**
     * 跳转到编辑页面
     * @param id
     * @param map
     * @return
     */
    @GetMapping("edit")
    public String toEdit(@RequestParam(defaultValue = "-1") int id,ModelMap map){
        if(id!=-1){
            MyOrder myOrder = myOrderService.selectById(id);
            map.addAttribute("myOrder",myOrder);
        }
        return "order/edit";
    }
//
    /**
     * 删除商品方法
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
            myOrderService.delete(Integer.valueOf(delId));
        }
        return WebResult.success();
    }

    /**
     * 更新商品方法
     * @param order
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public WebResult edit(MyOrder order){
        boolean flag;
        //直接更新
        flag= myOrderService.updateOrder(order);
        return returnResult(flag);
    }


    public WebResult returnResult(boolean flag){
        if(flag) return WebResult.success();
        else return WebResult.unknown();
    }

}
