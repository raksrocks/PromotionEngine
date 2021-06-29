package com.assessment.promotion.service.impl;

import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.exception.InvalidPromotionCodeException;
import com.assessment.promotion.exception.InvalidShoppingCartException;
import com.assessment.promotion.model.Product;
import com.assessment.promotion.model.ShoppingCart;
import com.assessment.promotion.service.PromotionType;
import com.assessment.promotion.utils.CatalogueUtil;

import java.util.HashMap;
import java.util.Map;

public class SingleItemPromo implements PromotionType {

    private final String sku;
    private final Integer quantity;
    private final Double discountedPrice;

    public SingleItemPromo(String sku, Integer quantity, Double discountedPrice) {
        this.sku = sku;
        this.quantity = quantity;
        this.discountedPrice = discountedPrice;
    }

    /*
        Takes cart as input, applies the promotion and returns remaining cart
     */
    @Override
    public ShoppingCart applyPromotion(ShoppingCart cart) throws InvalidPromotionCodeException,  InvalidShoppingCartException {

        if(cart.getCartContents().isEmpty())
            return cart;

        if(!isAvailable(cart))
            throw new InvalidPromotionCodeException("Single Item Promotion is not applicable for your cart");

        ShoppingCart promoCart = new ShoppingCart(cart.getCartContents());

        int cartQuantity = promoCart.getQuantity(sku);
        Map<Product, Integer> updatedContents = new HashMap<>();

        if(cartQuantity-quantity == 0)
            updatedContents.putAll(promoCart.removeItem(sku));
        else
            updatedContents.putAll(promoCart.replaceItem(sku, cartQuantity-quantity));

        promoCart.setCartContents(updatedContents);
        return promoCart;
    }

    /*
        Check if the current promotion type is applicable on the contents of the cart.
        Verify if min quantity criteria is satisfied
     */
    public boolean isAvailable(ShoppingCart cart) {

        // Fix object match issue
        for(Map.Entry<Product, Integer> kv: cart.getCartContents().entrySet()){
            if(kv.getKey().getName().equalsIgnoreCase(sku) && kv.getValue() >= quantity) // min quantity check
                return true;
        }
        return false;
    }

    /*
           returns the discount offered by this promotion
    */
    public Double getDiscountedPrice() throws InvalidProductException {
        return (CatalogueUtil.getPrice(sku) * this.quantity) - this.discountedPrice;
    }

    @Override
    public String toString() {
        return "SingleItemPromo{" +
                "sku='" + sku + '\'' +
                ", quantity=" + quantity +
                ", discountedPrice=" + discountedPrice +
                '}';
    }
}
