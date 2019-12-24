package com.tim.mall.web.Service.impl;

import com.tim.mall.model.User;
import com.tim.mall.web.Mapper.ShippingMapper;
import com.tim.mall.web.Model.Shipping;
import com.tim.mall.web.Service.ShippingServcie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class ShippingServiceImpl implements ShippingServcie {

    @Resource
    private ShippingMapper shippingMapper;

    @Override
    public Integer insert(Shipping shipping) {
       return shippingMapper.insert(shipping);
    }

    @Override
    public Shipping selectByUser(User current_user) {
        if(current_user!=null)
            return shippingMapper.selectByUser(current_user.getId());
        else
            return new Shipping();
    }

    @Override
    public Integer setShipping(Shipping shipping, User current_user) {
        shipping.setUserId(current_user.getId());
        shipping.setCreateTime(new Date());
        return shippingMapper.insert(shipping);
    }
}
