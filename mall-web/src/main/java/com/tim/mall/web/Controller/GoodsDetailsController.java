package com.tim.mall.web.Controller;

import com.tim.mall.model.Goods;
import com.tim.mall.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class GoodsDetailsController {

    @Resource
    private GoodsService goodsService;
    /**
     * 进入商品详情页
     * @param name
     * @param map
     * @return
     */
    @RequestMapping("goods_details")
    public String toGoodsDetails(@RequestParam(defaultValue = "")String name, ModelMap map){
        Goods good = goodsService.selectByName_NoLimit(name);
        map.addAttribute("good",good);
        return "goods_details";
    }
}
