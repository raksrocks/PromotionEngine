package com.assessment.promotion.service.impl;

import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.exception.InvalidPromotionCodeException;
import com.assessment.promotion.model.Product;
import com.assessment.promotion.model.ShoppingCart;
import com.assessment.promotion.service.PromotionType;
import com.assessment.promotion.utils.CatalogueUtil;

public class SingleItemPromo implements PromotionType {

    private final String sku;
    private final Integer quantity;
    private final Double discountedPrice;

    public SingleItemPromo(String sku, Integer quantity, Double discountedPrice) {
        this.sku = sku;
        this.quantity = quantity;
        this.discountedPrice = discountedPrice;
    }

    @Override
    public ShoppingCart applyPromotion(ShoppingCart cart) throws InvalidPromotionCodeException, InvalidProductException {
        if(!isAvailable(cart))
            throw new InvalidPromotionCodeException("Single Item Promotion is not applicable for your cart");

        ShoppingCart promoCart = new ShoppingCart(cart.getCartContents());
        Product requestedProduct = new Product(sku);
        int cartQuantity = promoCart.getCartContents().get(requestedProduct);

        if(cartQuantity-quantity == 0)
            promoCart.getCartContents().remove(requestedProduct);
        else
            promoCart.getCartContents().put(new Product(sku), cartQuantity-quantity);

        return promoCart;
    }

    public boolean isAvailable(ShoppingCart cart) throws InvalidProductException {
        return cart.getCartContents().containsKey(new Product(sku));
    }

    public Double getDiscountedPrice() throws InvalidProductException {
        return (CatalogueUtil.getPrice(sku) * this.quantity) - this.discountedPrice;
    }

}
