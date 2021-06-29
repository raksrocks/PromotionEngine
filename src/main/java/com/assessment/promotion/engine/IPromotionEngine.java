package com.assessment.promotion.engine;

import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.exception.InvalidPromotionCodeException;
import com.assessment.promotion.exception.InvalidShoppingCartException;
import com.assessment.promotion.model.ShoppingCart;
import com.assessment.promotion.service.PromotionType;

import java.util.List;

public interface IPromotionEngine {

    public Double getFullPrice(ShoppingCart cart) throws InvalidProductException;

    public Double getDiscountedPrice(ShoppingCart cart,  List<PromotionType> allPromotionTypes) throws InvalidProductException, InvalidPromotionCodeException, InvalidShoppingCartException;
}
