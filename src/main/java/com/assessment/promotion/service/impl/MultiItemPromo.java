package com.assessment.promotion.service.impl;

import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.exception.InvalidPromotionCodeException;
import com.assessment.promotion.model.Product;
import com.assessment.promotion.model.ShoppingCart;
import com.assessment.promotion.service.PromotionType;
import com.assessment.promotion.utils.CatalogueUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultiItemPromo implements PromotionType {

    private List<String> skuList = new ArrayList<>();
    private Double discountedPrice = 0.0;

    public MultiItemPromo(List<String> skus, Double discountedPrice) {
        this.skuList.addAll(skus);
        this.discountedPrice = discountedPrice;
    }

    public ShoppingCart applyPromotion(ShoppingCart cart) throws InvalidPromotionCodeException, InvalidProductException {
        if(!isAvailable(cart))
            throw new InvalidPromotionCodeException("Multi Item promotion is not applicable for your cart");

        ShoppingCart discountedCart = new ShoppingCart(cart.getCartContents());
        Map<Product, Integer> cartContents = discountedCart.getCartContents();

        for(String sku: skuList){
            Product prod = new Product(sku);
            if(cartContents.get(prod)==1)
                cartContents.remove(prod);
            else
                cartContents.put(prod,cartContents.get(prod)-1);
        }

        return discountedCart;
    }

    @Override
    public boolean isAvailable(ShoppingCart cart) {

        for(String sku: skuList)
            if(!cart.getCartContents().containsKey(sku))
                return false;
        return true;
    }

    public Double getDiscountedPrice() throws InvalidPromotionCodeException, InvalidProductException {
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
