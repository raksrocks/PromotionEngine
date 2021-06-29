package com.assessment.promotion.service.impl;

import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.exception.InvalidPromotionCodeException;
import com.assessment.promotion.exception.InvalidShoppingCartException;
import com.assessment.promotion.model.Product;
import com.assessment.promotion.model.ShoppingCart;
import com.assessment.promotion.service.PromotionType;
import com.assessment.promotion.utils.CatalogueUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiItemPromo implements PromotionType {

    private List<String> skuList = new ArrayList<>();
    private Double discountedPrice = 0.0;

    public MultiItemPromo(List<String> skus, Double discountedPrice) {
        this.skuList.addAll(skus);
        this.discountedPrice = discountedPrice;
    }

    public ShoppingCart applyPromotion(ShoppingCart cart) throws InvalidPromotionCodeException, InvalidShoppingCartException {
        if(!isAvailable(cart))
            throw new InvalidPromotionCodeException("Multi Item promotion is not applicable for your cart");

        ShoppingCart promoCart = new ShoppingCart(cart.getCartContents());
        Map<Product, Integer> cartContents = new HashMap<>();
        cartContents.putAll(cart.getCartContents());
        for(String sku: skuList){
            if(promoCart.getQuantity(sku)==1)
                cartContents.remove(promoCart.getEntryBySKU(sku));
           else
                cartContents.putAll(promoCart.replaceItem(sku,promoCart.getQuantity(sku)-1));
        }
        promoCart.setCartContents(cartContents);
        return promoCart;
    }

    @Override
    public boolean isAvailable(ShoppingCart cart) {

        boolean isSKUMatched = false;
        boolean isQuantityMatched = false;
        Map<String, Boolean> matches = new HashMap<>();

        skuList.forEach(sku -> matches.put(sku, false));

        for(Map.Entry<Product, Integer> kv: cart.getCartContents().entrySet()){
            if(skuList.contains(kv.getKey().getName()))
               matches.put(kv.getKey().getName(),true);
        }

        return !matches.values().contains(false);

    }

    public Double getDiscountedPrice() throws  InvalidProductException {
        double itemPrice = 0.0;
        for(String sku: skuList)
            itemPrice += CatalogueUtil.getPrice(sku);

        return itemPrice - this.discountedPrice;
    }

    @Override
    public String toString() {
        return "MultiItemPromo{" +
                "skuList=" + skuList +
                ", discountedPrice=" + discountedPrice +
                '}';
    }
}
