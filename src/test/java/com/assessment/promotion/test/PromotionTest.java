package com.assessment.promotion.test;

import com.assessment.promotion.engine.IPromotionEngine;
import com.assessment.promotion.engine.PromotionEngine;
import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.exception.InvalidPromotionCodeException;
import com.assessment.promotion.exception.InvalidShoppingCartException;
import com.assessment.promotion.model.Product;
import com.assessment.promotion.model.ShoppingCart;
import com.assessment.promotion.service.PromotionType;
import com.assessment.promotion.service.impl.MultiItemPromo;
import com.assessment.promotion.service.impl.SingleItemPromo;
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
    public void testIsAvailableSingleItem() throws InvalidProductException, InvalidShoppingCartException {
        PromotionType promo = new SingleItemPromo("A",2,100.0);
        cart.clear();
        cart.setCartContents(Map.of(new Product("A") ,3));
        boolean actual = promo.isAvailable(cart);
        Assertions.assertTrue(actual);
    }

    @Test
    public void testIsAvailableMultiItem() throws InvalidProductException, InvalidShoppingCartException {
        PromotionType promo = new MultiItemPromo(List.of("A","B"),100.0);
        cart.clear();
        cart.setCartContents(Map.of(new Product("A") ,3));
        boolean actual = promo.isAvailable(cart);
        Assertions.assertFalse(actual);
    }

    @Test
    @DisplayName("When SinglePromotionType Applied then Validate Cart Value")
    public void whenSinglePromotionTypeApplied_thenValidateCartValue() throws InvalidProductException, InvalidShoppingCartException, InvalidPromotionCodeException {
        Double  expectedPrice = 130.0;
        cart.clear();
        cart.setCartContents(Map.of(new Product("A") ,3));
        Double actualPrice = promoEngine.getDiscountedPrice(cart, promotionsList);
        Assertions.assertEquals(expectedPrice,actualPrice);
    }

    @Test
    @DisplayName("When SinglePromotionType Applied on Combo cart then Validate Cart Value")
    public void whenSinglePromotionTypeAppliedComboCart_thenValidateCartValue() throws InvalidProductException, InvalidShoppingCartException, InvalidPromotionCodeException {
        Double  expectedPrice = 160.0;
        cart.clear();
        cart.setCartContents(Map.of(new Product("A") ,3, new Product("B") ,1));
        Double actualPrice = promoEngine.getDiscountedPrice(cart, promotionsList);
        Assertions.assertEquals(expectedPrice,actualPrice);
    }

    @Test
    @DisplayName("When SinglePromotionType Applied on Combo cart and combo promotion then Validate Cart Value")
    public void whenSinglePromotionTypeAppliedComboCartAndPromotion_thenValidateCartValue() throws InvalidProductException, InvalidShoppingCartException, InvalidPromotionCodeException {
        Double  expectedPrice = 175.0;
        cart.clear();
        cart.setCartContents(Map.of(new Product("A") ,3, new Product("B") ,2));
        Double actualPrice = promoEngine.getDiscountedPrice(cart, promotionsList);
        Assertions.assertEquals(expectedPrice,actualPrice);
    }


    @Test
    public void whenComboPromotionTypeApplied_thenValidateCartValue() throws InvalidProductException, InvalidPromotionCodeException, InvalidShoppingCartException {
        Double  expectedPrice = 30.0;
        cart.clear();
        cart.setCartContents(Map.of(new Product("C") ,1, new Product("D") ,1));
        Double actualPrice = promoEngine.getDiscountedPrice(cart, promotionsList);
        Assertions.assertEquals(expectedPrice,actualPrice);
    }

    @Test
    public void whenComboPromotionTypeAppliedCombo_thenValidateCartValue() throws InvalidProductException, InvalidPromotionCodeException, InvalidShoppingCartException {
        Double  expectedPrice = 80.0;
        cart.clear();
        cart.setCartContents(Map.of(new Product("C") ,3, new Product("D") ,2));
        Double actualPrice = promoEngine.getDiscountedPrice(cart, promotionsList);
        Assertions.assertEquals(expectedPrice,actualPrice);
    }

    @Test
    public void whenSinglePromotionTypeNotApplied_thenValidateCartValue() throws InvalidProductException, InvalidPromotionCodeException, InvalidShoppingCartException {
        Double  expectedPrice = 100.0;
        cart.clear();
        cart.setCartContents(Map.of(new Product("A") ,2));
        Double actualPrice = promoEngine.getDiscountedPrice(cart, promotionsList);
        Assertions.assertEquals(expectedPrice,actualPrice);
    }

    @Test
    public void whenComboPromotionTypeNotApplied_thenValidateCartValue() throws InvalidProductException, InvalidPromotionCodeException, InvalidShoppingCartException {
        Double  expectedPrice = 100.0;
        cart.clear();
        cart.setCartContents(
                Map.of(new Product("A") ,1,
                       new Product("B") ,1,
                       new Product("C") ,1));
        Double actualPrice = promoEngine.getDiscountedPrice(cart, promotionsList);
        Assertions.assertEquals(expectedPrice,actualPrice);
    }

    @Test
    public void whenNoPromotionTypeApplied_thenValidateCartValue(){

    }


}
