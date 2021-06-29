package com.assessment.promotion.engine;

import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.exception.InvalidPromotionCodeException;
import com.assessment.promotion.exception.InvalidShoppingCartException;
import com.assessment.promotion.model.Product;
import com.assessment.promotion.model.ShoppingCart;
import com.assessment.promotion.service.PromotionType;
import com.assessment.promotion.utils.CatalogueUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PromotionEngine implements IPromotionEngine{

    public Double getFullPrice(ShoppingCart cart) throws InvalidProductException {
        double fullPrice = 0.0;

       // Map<Product, Integer> cartContents = cart.getCartContents();
        for(Map.Entry<Product,Integer> entry: cart.getCartContents().entrySet()){
            fullPrice += entry.getValue() * CatalogueUtil.getPrice(entry.getKey().getName());
        }
        return fullPrice;
    }

    public Double getDiscountedPrice(ShoppingCart cart,  List<PromotionType> allPromotionTypes) throws InvalidProductException, InvalidPromotionCodeException, InvalidShoppingCartException {
        double promoPrice =  getFullPrice(cart);

        return applyAvailablePromotion(cart,allPromotionTypes,promoPrice);
        //return promoPrice;
    }

    private Double applyAvailablePromotion(ShoppingCart cart, List<PromotionType> allPromos, Double cartPrice) throws InvalidPromotionCodeException, InvalidProductException, InvalidShoppingCartException {

        List<PromotionType> availablePromos = new ArrayList<PromotionType>();
        for(PromotionType promoType: allPromos) {
            System.out.println(promoType.toString()+" "+promoType.isAvailable(cart));
            if (promoType.isAvailable(cart))
                availablePromos.add(promoType);
        }

        // unit test fix - breaking the recursive loop
        if(availablePromos.isEmpty())
            return cartPrice;

        PromotionType selectedPromo = null;
        double discountedPrice = 0.0;

        for(PromotionType promotion: availablePromos){
            double offerPrice = promotion.getDiscountedPrice();
            if(offerPrice > discountedPrice){
                discountedPrice = offerPrice;
                selectedPromo = promotion;
            }
        }

        ShoppingCart cartWithPromo = new ShoppingCart();
        if(null != selectedPromo){
            cartWithPromo = selectedPromo.applyPromotion(cart);
        }
        cartPrice -= discountedPrice;

        return applyAvailablePromotion(cartWithPromo, availablePromos, cartPrice);
    }

}
