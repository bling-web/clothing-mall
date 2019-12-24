package com.tim.mall.web.Controller;

import com.tim.mall.model.Goods;
import com.tim.mall.service.GoodsService;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private GoodsService goodsService;

    /**
     * 进入主页面方法
     * @param map
     * @param query
     * @param page
     * @param
     * @return
     */
    @RequestMapping("index")
    public String toIndex(ModelMap map, @RequestParam(defaultValue="") String query, @RequestParam(defaultValue="1")Integer page
    ){
        List<Goods> goods_img = goodsService.selectGood_ImgByName(query, page-1, 10);
        map.addAttribute("goods_img",goods_img);
        map.addAttribute("query",query);
        return "index";
    }
    /**
     * 进入分页面方法(男女装)
     * @param map
     * @param query
     * @param page
     * @param categoryId
     * @return
     */
    @RequestMapping("category")
    public String toCategory(ModelMap map, @RequestParam(defaultValue="")String query,@RequestParam(defaultValue="1")Integer page
            ,@RequestParam(defaultValue="-1")int categoryId,@RequestParam(defaultValue="")String price ){
        List<Goods> goods_img;
        if(price.equals("")){
            //无价格排序
            goods_img = goodsService.selectCategoryGoods_ImgByName(categoryId, query, page - 1, 10);
        }
        else{
            //有价格排序
            if(price.equals("asc"))
                goods_img = goodsService.selectCategoryGoods_ImgByName_PriceAsc(categoryId,query, page - 1, 10);
            else
                goods_img = goodsService.selectCategoryGoods_ImgByName_PriceDesc(categoryId,query, page - 1, 10);
        }
        map.addAttribute("goods_img",goods_img);
        map.addAttribute("query",query);
        map.addAttribute("categoryId",categoryId);
        return "category";
    }
}
