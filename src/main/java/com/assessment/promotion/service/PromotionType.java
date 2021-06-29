package com.assessment.promotion.service;

import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.exception.InvalidPromotionCodeException;
import com.assessment.promotion.exception.InvalidShoppingCartException;
import com.assessment.promotion.model.ShoppingCart;

public interface PromotionType {

    ShoppingCart applyPromotion(ShoppingCart cart) throws InvalidPromotionCodeException, InvalidProductException, InvalidShoppingCartException;

    boolean isAvailable(ShoppingCart cart) throws InvalidProductException;

    public Double getDiscountedPrice() throws InvalidPromotionCodeException, InvalidProductException;
}
