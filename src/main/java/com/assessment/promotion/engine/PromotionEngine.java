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


public class PromotionEngine implements IPromotionEngine{

    /*
        Returns full price of the cart without adding any promotions to it
     */
    public Double getFullPrice(ShoppingCart cart) throws InvalidProductException {
        double fullPrice = 0.0;

        for(Map.Entry<Product,Integer> entry: cart.getCartContents().entrySet()){
            fullPrice += entry.getValue() * CatalogueUtil.getPrice(entry.getKey().getName());
        }
        return fullPrice;
    }

    /*
        Returns discounted price after applying te promotions.
        Takes list of promotion types as the input and returns the price
     */
    public Double getDiscountedPrice(ShoppingCart cart,  List<PromotionType> allPromotionTypes) throws InvalidProductException, InvalidPromotionCodeException, InvalidShoppingCartException {
        double promoPrice =  getFullPrice(cart);

        return applyAvailablePromotion(cart,allPromotionTypes,promoPrice);
    }

    /*
        Applies applicable promotions recursively until no applicable promotions left.
        A copy of cart items will be reduced in iterations over and over until no items/no promotions left.

        This generic logic works with any future promotion types as well.
     */
    private Double applyAvailablePromotion(ShoppingCart cart, List<PromotionType> allPromos, Double cartPrice) throws InvalidPromotionCodeException, InvalidProductException, InvalidShoppingCartException {

        List<PromotionType> availablePromos = new ArrayList<PromotionType>();
        for(PromotionType promoType: allPromos)
            if (promoType.isAvailable(cart))
                availablePromos.add(promoType);

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
        // reduce te cart items using the reduction method implemented on the promotion class
        ShoppingCart cartWithPromo = new ShoppingCart();
        if(null != selectedPromo){
            cartWithPromo = selectedPromo.applyPromotion(cart);
        }
        cartPrice -= discountedPrice;

        // call the same method with resulting cart items
        return applyAvailablePromotion(cartWithPromo, availablePromos, cartPrice);
    }

}
