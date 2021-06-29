package com.assessment.promotion.test;

import com.assessment.promotion.engine.IPromotionEngine;
import com.assessment.promotion.engine.PromotionEngine;
import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.exception.InvalidPromotionCodeException;
import com.assessment.promotion.exception.InvalidShoppingCartException;
import com.assessment.promotion.model.Product;
import com.assessment.promotion.model.ShoppingCart;
import com.assessment.promotion.service.PromotionType;
import com.assessment.promotion.utils.PromotionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class PromotionTest {

    private static IPromotionEngine promoEngine;
    private static List<PromotionType> promotionsList;
    private static ShoppingCart cart;

    @BeforeAll
    public static void addPromotions(){
        promoEngine = new PromotionEngine();
        promotionsList = PromotionUtil.createPromotions();
        cart = new ShoppingCart();

    }

    @Test
    @DisplayName("when SinglePromotionType Applied then Validate Cart Value")
    public void whenSinglePromotionTypeApplied_thenValidateCartValue() throws InvalidProductException, InvalidShoppingCartException, InvalidPromotionCodeException {
        Double  expectedPrice = 130.0;
        cart.clear();
        cart.setCartContents(Map.of(new Product("A") ,3));
        Double actualPrice = promoEngine.getDiscountedPrice(cart, promotionsList);
        Assertions.assertEquals(expectedPrice,actualPrice);
    }

    @Test
    public void whenComboPromotionTypeApplied_thenValidateCartValue(){

    }

    @Test
    public void whenSinglePromotionTypeNotApplied_thenValidateCartValue(){

    }

    @Test
    public void whenComboPromotionTypeNotApplied_thenValidateCartValue(){

    }

    @Test
    public void whenNoPromotionTypeApplied_thenValidateCartValue(){

    }


}
