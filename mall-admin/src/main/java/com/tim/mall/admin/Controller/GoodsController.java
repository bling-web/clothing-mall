package com.tim.mall.admin.Controller;


import com.tim.mall.admin.common.WebResult;
import com.tim.mall.model.Goods;
import com.tim.mall.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("admin/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;
    /**
     * 进入角色管理方法
     * 在页面中每一次搜索,每一次选页面的显示条数都会重新提交表单
     * @return
     */
    @RequestMapping
    public String ToGoodsIndex(@RequestParam(defaultValue = "10")int pageSize, ModelMap map,@RequestParam(defaultValue = "-1") int categoryId,
                             @RequestParam(defaultValue = "")String query, @RequestParam(defaultValue = "1") int page ){
        List<Goods> goods;
        int count;
        if(categoryId==-1){
            //没有分类的查询
            goods= goodsService.selectByName(query,(page-1)*pageSize,pageSize);
            //查询总长度
            count=goodsService.selectCount(query);
        }else{
            //查询分类和搜索的内容
            goods=goodsService.selectCategoryByName(query,(page-1)*pageSize,pageSize,categoryId);
            count=goodsService.selectCategoryCount(query,categoryId);
        }
        map.addAttribute("categoryId",categoryId);
        map.addAttribute("goods",goods);
        map.addAttribute("count",count);
        int maxPage=count/10==0 ? count/10 :(count/10)+1;
        map.addAttribute("maxPage",maxPage);
        map.addAttribute("page",page);
        map.addAttribute("pageSize",pageSize);
        map.addAttribute("query",query);
        //查询的当前页面的数据在整个页面的位置(排序)
        map.addAttribute("nowBegin",pageSize*(page-1)+1);
        map.addAttribute("nowEnd" ,pageSize*(page-1)+goods.size());
        return "goods/index";
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
            Goods good = goodsService.selectById(id);
            map.addAttribute("good",good);
        }
        return "goods/edit";
    }

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
        Goods goods = new Goods();
        for (String delId : ids) {
            goods.setId(Integer.valueOf(delId));
            goodsService.delete(goods);
        }
        return WebResult.success();
    }

    /**
     * 更新商品方法
     * @param good
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public WebResult edit(Goods good){
        boolean flag;
        if(good.getId()!=null){
            //说明不是新增用户,直接更新
            flag= goodsService.updateGood(good);
            return returnResult(flag);
        }else{
            good.setCreateTime(new Date());
            good.setUpdateTime(new Date());
            flag = goodsService.insertUser(good);
            return returnResult(flag);
        }
    }


    public WebResult returnResult(boolean flag){
        if(flag) return WebResult.success();
        else return WebResult.unknown();
    }

}
