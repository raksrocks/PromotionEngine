package com.assessment.promotion.model;

import java.util.Map;

public class ShoppingCart {
    private Map<Product,Integer> cartContents;

    public Map<Product, Integer> getCartContents() {
        return cartContents;
    }

    public void setCartContents(Map<Product, Integer> cartContents) {
        this.cartContents = cartContents;
    }

    public ShoppingCart(Map<Product, Integer> cartContents) {
        this.cartContents = cartContents;
    }

    public ShoppingCart() {
    }
}
