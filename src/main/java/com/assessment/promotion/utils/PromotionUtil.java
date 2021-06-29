package com.assessment.promotion.utils;

import com.assessment.promotion.service.PromotionType;
import com.assessment.promotion.service.impl.MultiItemPromo;
import com.assessment.promotion.service.impl.SingleItemPromo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PromotionUtil {

    public static List<PromotionType> createPromotions(){
        List<PromotionType> promos = new ArrayList<>();
        promos.add(new SingleItemPromo("A",3, 130.0));
        promos.add(new SingleItemPromo("B",2, 45.0));
        promos.add(new MultiItemPromo(List.of("C","D"), 130.0));

        return promos;
    }
}
