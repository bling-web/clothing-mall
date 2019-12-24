package com.tim.mall.web.Controller;

import com.tim.mall.model.User;
import com.tim.mall.service.GoodsService;
import com.tim.mall.web.Model.Cart;
import com.tim.mall.web.Service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CartController {
    @Resource
    private CartService cartService;

    @Resource
    private GoodsService goodsService;

    @RequestMapping("cart")
    public String toCart(HttpSession session, Cart cart_direct, @RequestParam(defaultValue = "-1") String get_id
    , ModelMap map){
        //判断是否登录
        User user = (User) session.getAttribute("current_user");
        //没有登录,记录当前cart参数,转发到登录页面
        if(user==null){
            session.setAttribute("cart",cart_direct);
            return "redirect:/sign_in";
        }
        //存储当前商品数据到cart对象
        Cart cart=cartService.createCart(session,get_id,cart_direct);
        //将其存储到cart表中
        insertCart(cart, user);
        //查询数据库购物车表,返回购物车页面
        //注意要返回该当前用户的
        List<Cart> carts = cartService.selectAll_currentUser(user.getId());
        map.addAttribute("carts",carts);
        return "cart";
    }

    public void insertCart(Cart cart,User user){
        //判断该购物信息是否已经存储
        List<String> names = cartService.selectAllName();
        int quantity=0;
        if(cart.getQuantity()!=null){
            quantity=cart.getQuantity();
        }
        if(names.contains(cart.getProductName())){
            cart.setTotal(Double.valueOf(cart.getProductPrice())*quantity);
            //如果有直接进行更新,注意要更新每个用户的
            cartService.update(cart,user);
        }else{
            if(cart.getProductName()!=null){
                //在字段不为空的情况下进行新增
                cart.setTotal(Double.valueOf(cart.getProductPrice())*quantity);
                cartService.insert(cart,user);
            }
        }
    }

    @GetMapping("updateCart")
    public String updateCart(ModelMap map){
        List<Cart> carts = cartService.selectAll();
        map.addAttribute("carts",carts);
        return "cart";
    }

    @RequestMapping("deleteCart")
    public String deleteCart(@RequestParam("id") int id){
        if(id==0) return "redirect:cart";
        cartService.deleteById(Integer.valueOf(id));
        return "redirect:cart";

    }
}
