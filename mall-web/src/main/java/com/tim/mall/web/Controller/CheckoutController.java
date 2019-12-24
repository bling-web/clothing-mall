package com.tim.mall.web.Controller;

import com.tim.mall.model.User;
import com.tim.mall.web.Model.Cart;
import com.tim.mall.web.Model.Shipping;
import com.tim.mall.web.Service.CartService;
import com.tim.mall.web.Service.ShippingServcie;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CheckoutController {

    @Resource
    private CartService cartService;

    @Resource
    private ShippingServcie shippingServcie;

    @RequestMapping("/checkout")
    public String toCheckOut(@Param("checkbox") String checkbox, String help, String quantity
                             , HttpSession session){
        if(checkbox!=null) {
            String[] ids = checkbox.split(",");
            String[] help_id = help.split(",");
            String[] quantitys = quantity.split(",");
            //为所选商品创建订单数据,加入到List<Cart>中
            List<Cart> carts=cartService.createOrderDate(ids,help_id,quantitys);
            Double sum=0.00;
            for (Cart cart : carts) {
                sum += cart.getTotal();
            }
            session.setAttribute("checktout_cart",carts);
            session.setAttribute("sum",sum);
        }else{
            session.setAttribute("checktout_cart", new ArrayList<Cart>());
            session.setAttribute("sum",0);
        }
        //查询该用户的地址
        Shipping shipping = shippingServcie.selectByUser((User) session.getAttribute("current_user"));
        session.setAttribute("shipping",shipping);
        return "checkout";
    }
}
