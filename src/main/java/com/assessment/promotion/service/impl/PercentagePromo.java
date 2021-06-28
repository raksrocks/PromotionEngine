package com.assessment.promotion.service.impl;

import com.assessment.promotion.model.ShoppingCart;
import com.assessment.promotion.service.PromotionType;

public class PercentagePromo implements PromotionType {

    public ShoppingCart applyPromotion(ShoppingCart cart) {
        return null;
    }

    public boolean isAvailable(ShoppingCart cart) {
        return false;
    }
}
