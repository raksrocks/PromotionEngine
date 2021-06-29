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

public class PromotionEngineTest {

    private static IPromotionEngine promoEngine;
    private static List<PromotionType> promotionsList;
    private static ShoppingCart cart;

    @BeforeAll
    public static void setup(){
        promoEngine = new PromotionEngine();
        promotionsList = PromotionUtil.createPromotions();
        cart = new ShoppingCart();
    }

    @Test
    @DisplayName("Scenario A : 1*A = 50 ~ 1*B = 30 ~ 1*C = 20 :: Total=100")
    public void whenNoPromoApplied_thenVerifyTheCartValue() throws InvalidProductException, InvalidShoppingCartException, InvalidPromotionCodeException {
        Double  expectedPrice = 100.0;
        cart.clear();
        cart.setCartContents(
                Map.of( new Product("A") ,1,
                        new Product("B") ,1,
                        new Product("C") ,1));
        Double actualPrice = promoEngine.getDiscountedPrice(cart, promotionsList);
        Assertions.assertEquals(expectedPrice,actualPrice);
    }

    @Test
    @DisplayName("Scenario B : 5*A = 130 + 2*50  ~ 5*B = 45 + 45 + 30 ~ 1*C = 20 :: Total=370")
    public void whenSinglePromoTypeApplied_thenVerifyTheCartValue() throws InvalidProductException, InvalidShoppingCartException, InvalidPromotionCodeException {
        Double  expectedPrice = 370.0;
        cart.clear();
        cart.setCartContents(
                Map.of( new Product("A") ,5,
                        new Product("B") ,5,
                        new Product("C") ,1));
        Double actualPrice = promoEngine.getDiscountedPrice(cart, promotionsList);
        Assertions.assertEquals(expectedPrice,actualPrice);

    }

    @Test
    @DisplayName("Scenario C : 3*A = 130 ~ 5*B = 45 45 + 1*30 ~ 1*C 1*D = 30 :: Total=190")
    public void whenComboPromoTypeApplied_thenVerifyTheCartValue() throws InvalidProductException, InvalidShoppingCartException, InvalidPromotionCodeException {
        Double  expectedPrice = 190.0;
        cart.clear();
        cart.setCartContents(
                Map.of( new Product("A") ,3,
                        new Product("B") ,5,
                        new Product("C") ,1,
                        new Product("D") ,1));
        Double actualPrice = promoEngine.getDiscountedPrice(cart, promotionsList);
        Assertions.assertEquals(expectedPrice,actualPrice);

    }

    @Test
    @DisplayName("Scenario MutualExclusive")
    public void whenBothPromoTypeApplied_thenVerifyTheyAreMutuallyExclusive() throws InvalidProductException, InvalidShoppingCartException, InvalidPromotionCodeException {
        Double  expectedPrice = 370.0;
        cart.clear();
        cart.setCartContents(
                Map.of( new Product("A") ,5,
                        new Product("B") ,5,
                        new Product("C") ,1));
        Double actualPrice = promoEngine.getDiscountedPrice(cart, promotionsList);
        Assertions.assertEquals(expectedPrice,actualPrice);

    }



}
