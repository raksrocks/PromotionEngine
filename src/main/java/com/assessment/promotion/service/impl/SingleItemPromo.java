package com.assessment.promotion.service.impl;

import com.assessment.promotion.model.ShoppingCart;
import com.assessment.promotion.service.PromotionType;

public class SingleItemPromo implements PromotionType {
    public ShoppingCart applyPromotion(ShoppingCart cart) {
        return null;
    }

    public boolean isAvailable(ShoppingCart cart) {
        return false;
    }
}
