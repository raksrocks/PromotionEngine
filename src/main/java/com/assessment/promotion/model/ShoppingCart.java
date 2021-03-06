package com.assessment.promotion.model;

import com.assessment.promotion.exception.InvalidShoppingCartException;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product,Integer> cartContents;

    public Map<Product, Integer> getCartContents() {
        return cartContents;
    }

    public void setCartContents(Map<Product, Integer> cartContents) throws InvalidShoppingCartException {
        //if(cartContents.isEmpty())
            //throw new InvalidShoppingCartException();

        this.cartContents = cartContents;
    }

    public ShoppingCart(Map<Product, Integer> cartContents) {
        this.cartContents = cartContents;
    }

    public ShoppingCart() {
    }

    public void clear(){
        this.cartContents = Map.of();
    }

    public Integer getQuantity(String sku){
        for(Map.Entry<Product, Integer> kv: this.cartContents.entrySet()){
            if(kv.getKey().getName().equalsIgnoreCase(sku))
                return kv.getValue();
        }
        return 0;
    }

    public Map<Product, Integer> removeItem(String sku){
        Product tobeRemoved = null;
        Map<Product, Integer> temp = new HashMap<>();
        temp.putAll(this.cartContents);

        for(Map.Entry<Product, Integer> kv: temp.entrySet()){
            if(kv.getKey().getName().equalsIgnoreCase(sku))
                tobeRemoved = kv.getKey();
        }
        if(null != tobeRemoved)
            temp.remove(tobeRemoved);

        return temp;
    }

    public Map<Product, Integer> replaceItem(String sku, Integer qty){
        Product toBeModified = null;
        Map<Product, Integer> temp = new HashMap<>();
        temp.putAll(this.cartContents);

        for(Map.Entry<Product, Integer> kv: temp.entrySet()){
            if(kv.getKey().getName().equalsIgnoreCase(sku))
                toBeModified = kv.getKey();
        }
        if(null != toBeModified)
            temp.put(toBeModified,qty);
        return temp;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartContents=" + cartContents.toString() +
                '}';
    }

    public Product getEntryBySKU(String sku){
        for(Map.Entry<Product, Integer> kv: cartContents.entrySet()){
            if(kv.getKey().getName().equalsIgnoreCase(sku))
                return kv.getKey();
        }
        return null;
    }
}
