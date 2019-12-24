package com.tim.mall.web.Service.impl;

import com.tim.mall.model.Goods;
import com.tim.mall.model.User;
import com.tim.mall.service.GoodsService;
import com.tim.mall.web.Mapper.CartMapper;
import com.tim.mall.web.Model.Cart;
import com.tim.mall.web.Service.CartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {


    @Resource
    private CartMapper cartMapper;

    @Resource
    private GoodsService goodsService;

    /**
     * 插入方法
     * @param cart
     * @param user
     * @return
     */
    @Override
    public boolean insert(Cart cart, User user) {
        cart.setUserId(user.getId());
        cart.setChecked(1);
        cart.setCreateTime(new Date());
        int flag = cartMapper.insert(cart);
        if(flag>1) return true;
        else return false;
    }

    /**
     * 查询全部方法
     * @return
     */
    @Override
    public List<Cart> selectAll() {
        List<Cart> carts = cartMapper.selectAll();
        return carts;
    }

    @Override
    public List<String> selectAllName() {
        return cartMapper.selectAllName();
    }

    @Override
    public void update(Cart cart, User user) {
        cart.setUpdateTime(new Date());
        cartMapper.updateByName(cart,user);
    }

    @Override
    public Cart selectById(Integer id) {
        return cartMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        cartMapper.deleteById(id);
    }

    @Override
    public void updateQuantityById(Integer id, Integer quantity, Integer total) {
        cartMapper.updateQuantityById(id,quantity,total);
    }

    @Override
    public List<Cart> selectAll_currentUser(Integer id) {
        return cartMapper.selectAll_currentUser(id);
    }

    @Override
    public void deleteGoodsFromCart(HttpSession session) {
        List<Cart> checktout_cart = (List<Cart>) session.getAttribute("checktout_cart");
        for (Cart cart : checktout_cart) {
            cartMapper.deleteById(cart.getId());
        }
    }

    /**
     * 创建cart对象,存储当前商品数据
     * @param session
     * @param get_id
     * @param cart_direct
     * @return
     */
    @Override
    public Cart createCart(HttpSession session, String get_id, Cart cart_direct) {
        Cart cart;
        //如果get_id是默认值,说明是从商品详情页跳转过来的
        if(get_id.equals("-1")){
            //如果session中有cart,说明是转发到登录页面又转发回来的,直接获取之前存储的cart
            Cart cart_redirect = (Cart) session.getAttribute("cart");
            if(cart_redirect!=null){
                cart=cart_redirect;
                //取一次后就要进行删除,否则影响后续使用
                session.removeAttribute("cart");
            }
            else cart=cart_direct;
            //如果有值,说明是从点击购物车小图标跳转过来的
        }else{
            //根据商品id从数据库商品表查询值
            Goods goods = goodsService.selectGoods_ImgById(Integer.valueOf(get_id));
            cart=new Cart();
            cart.setProductName(goods.getName());
            cart.setProductPrice(String.valueOf(goods.getSellingPrice()));
            cart.setProductImg(goods.getimageUrl());
            cart.setProductId(goods.getId());
            cart.setQuantity(1);
        }
        return cart;
    }

    @Override
    public List<Cart> createOrderDate(String[] ids, String[] help_id, String[] quantitys) {
        List<Cart> carts=new ArrayList<>();
        //标记某个id对应的商品当前的数量
        int i=0;
        for (String id : ids) {
            //找出客户勾选id对应的数量,并进行更新数量和总价
            for ( ; i < help_id.length; i++) {
                if(id.equals(help_id[i])){
                    //根据id查询单价
                    Cart cart = cartMapper.selectById(Integer.valueOf(id));
                    //更新数量和总价,可能页面有增减数量的操作
                    cartMapper.updateQuantityById(Integer.valueOf(id),Integer.valueOf(quantitys[i]),Integer.valueOf(quantitys[i])*Integer.valueOf(cart.getProductPrice()));
                    break;
                }
            }
            carts.add(cartMapper.selectById(Integer.valueOf(id)));
        }
        return carts;
    }

    @Override
    public void updateGoodsQuantity(HttpSession session) {
        List<Cart> checktout_cart = (List<Cart>) session.getAttribute("checktout_cart");
        for (Cart cart : checktout_cart) {
            int basicStockById = goodsService.selectBasicStockById(cart.getProductId());
            int quantity=basicStockById-cart.getQuantity();
            goodsService.updateGoodQuantity(cart.getProductId(),quantity);
        }
    }
}
