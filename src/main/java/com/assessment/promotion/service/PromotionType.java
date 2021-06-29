package com.assessment.promotion.service;

import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.exception.InvalidPromotionCodeException;
import com.assessment.promotion.model.ShoppingCart;

public interface PromotionType {

    ShoppingCart applyPromotion(ShoppingCart cart) throws InvalidPromotionCodeException, InvalidProductException;

    boolean isAvailable(ShoppingCart cart) throws InvalidProductException;

    public Double getDiscountedPrice() throws InvalidPromotionCodeException, InvalidProductException;
}
