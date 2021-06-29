package com.assessment.promotion.model;

import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.utils.CatalogueUtil;

public class Product {
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name) throws InvalidProductException {
        this.name = name;
        this.price = CatalogueUtil.getPrice(name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
