package com.assessment.promotion.engine;

import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.model.Product;
import com.assessment.promotion.model.ShoppingCart;
import com.assessment.promotion.service.PromotionType;
import com.assessment.promotion.utils.CatalogueUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PromotionEngine {

    public Double getFullPrice(ShoppingCart cart) throws InvalidProductException {
        double fullPrice = 0.0;

       // Map<Product, Integer> cartContents = cart.getCartContents();
        for(Map.Entry<Product,Integer> entry: cart.getCartContents().entrySet()){
            fullPrice += entry.getValue() * CatalogueUtil.getPrice(entry.getKey().getName());
        }
        return fullPrice;
    }

    public Double getDiscountedPrice(ShoppingCart cart, Double currentPrice, List<PromotionType> allPromotionTypes) throws InvalidProductException {
        double promoPrice = 0.0;

        List<PromotionType> availablePromos = new ArrayList<PromotionType>();
        for(PromotionType promoType: allPromotionTypes)
            if(promoType.isAvailable(cart))
                availablePromos.add(promoType);

        if(availablePromos.isEmpty())
            return getFullPrice(cart);

        return promoPrice;
    }

}
