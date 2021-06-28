package com.assessment.promotion.service;

import com.assessment.promotion.model.ShoppingCart;

public interface PromotionType {

    ShoppingCart applyPromotion(ShoppingCart cart);

    boolean isAvailable(ShoppingCart cart);
}
