package com.assessment.promotion.test;

import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.exception.InvalidShoppingCartException;
import com.assessment.promotion.model.Product;
import com.assessment.promotion.model.ShoppingCart;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartTest {

    private static ShoppingCart cart;

    @BeforeAll
    public static void addItemsToCart() throws Exception{
        cart = new ShoppingCart();
        cart.setCartContents(
                Map.ofEntries(
                        Map.entry(new Product("A",50),1),
                        Map.entry(new Product("B",30),1),
                        Map.entry(new Product("C",20),1),
                        Map.entry(new Product("D",15),1)
                ));
    }

    @Test
    @DisplayName("When Cart Items Are Valid Then No Error")
    public void whenCartItemsAreValid_thenNoError(){
        cart.clear();
        Assertions.assertDoesNotThrow(()->cart.setCartContents(Map.ofEntries(
                Map.entry(new Product("A"),1),
                Map.entry(new Product("B"),1),
                Map.entry(new Product("C"),1)
        )));
    }

    @Test
    @DisplayName("When Cart Items Are Invalid Then No Error")
    public void whenCartItemsAreInvalid_thenError(){
        cart.clear();
        Assertions.assertThrows(InvalidShoppingCartException.class, ()->cart.setCartContents(new HashMap<Product,Integer>()));
    }

    @Test
    @DisplayName("When Cart Items Are Incorrect Then No Error")
    public void whenCartItemsAreIncorrect_thenError(){
        cart.clear();
        Assertions.assertThrows(InvalidProductException.class, ()->cart.setCartContents(Map.ofEntries(
                Map.entry(new Product("A"),1),
                Map.entry(new Product("B"),1),
                Map.entry(new Product("C"),1),
                Map.entry(new Product("E"),1)
        )));
    }
}
