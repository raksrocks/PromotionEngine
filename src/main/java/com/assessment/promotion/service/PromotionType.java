package com.assessment.promotion.service;

import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.exception.InvalidPromotionCodeException;
import com.assessment.promotion.exception.InvalidShoppingCartException;
import com.assessment.promotion.model.ShoppingCart;

public interface PromotionType {

    /*
        Promotion is applied once on the cart and returns the remaining cart.
     */
    ShoppingCart applyPromotion(ShoppingCart cart) throws InvalidPromotionCodeException, InvalidProductException, InvalidShoppingCartException;

    /*
        return true if the cart contents are valid for this promotion type
     */
    boolean isAvailable(ShoppingCart cart) throws InvalidProductException;

    /*
        returns the discount offered in this promo type
     */
    Double getDiscountedPrice() throws InvalidPromotionCodeException, InvalidProductException;
}
