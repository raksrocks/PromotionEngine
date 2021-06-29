package com.assessment.promotion.service.impl;

import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.exception.InvalidPromotionCodeException;
import com.assessment.promotion.model.ShoppingCart;
import com.assessment.promotion.service.PromotionType;

public class PercentagePromo implements PromotionType {
    // For future implementations
    public ShoppingCart applyPromotion(ShoppingCart cart) {
        return null;
    }

    // For future implementations
    public boolean isAvailable(ShoppingCart cart) {
        return false;
    }

    @Override
    public Double getDiscountedPrice() throws InvalidPromotionCodeException, InvalidProductException {
        return null;
    }
}
