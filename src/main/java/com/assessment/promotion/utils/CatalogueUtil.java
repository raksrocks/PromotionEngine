package com.assessment.promotion.utils;

import com.assessment.promotion.exception.InvalidProductException;
import com.assessment.promotion.model.Product;

import java.util.HashMap;
import java.util.Map;

public class CatalogueUtil {

    private static Map<String,Double> products = new HashMap<String,Double>();
    static
    {
        products.put("A",50.0);
        products.put("B",30.0);
        products.put("C",20.0);
        products.put("D",15.0);

    }

    public static Double getPrice(String productName) throws InvalidProductException {
        if(!products.containsKey(productName))
            throw new InvalidProductException(productName+ " is not a valid SKU");
        return products.get(productName);
    }
}
