package com.tim.mall.web.Service;

import com.tim.mall.model.User;
import com.tim.mall.web.Model.Shipping;

public interface ShippingServcie {
    Integer insert(Shipping shipping);

    Shipping selectByUser(User current_user);

    Integer setShipping(Shipping shipping, User current_user);
}
