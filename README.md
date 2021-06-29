# PromotionEngine

This Promotion Engine runs on a cart which contains products with SKUs ( A, B, C ... ) and calculates the total value after applying applicable promotions.

Test Setup:
1. Add product SKUs and their unit price in CatalogueUtil.java
    Example:
         
         products.put("A",50.0);         
         products.put("B",30.0);         
         products.put("C",20.0);         
         products.put("D",15.0);

2. Create different types of promotions by implementing the interface PromotionType.java.
3. Below implementations are already implemented in the packaged solution
     1. Single Item Promotion
         example:                
                3 of A's for 130
                2 od B's for 45                
     2. Multi Item Promotion
          example:               
               C & D for 30
4. Add promotions to the package using PromotionUtil.java
     Example:
     
        promos.add(new SingleItemPromo("A",3, 130.0));
        promos.add(new SingleItemPromo("B",2, 45.0));
        promos.add(new MultiItemPromo(List.of("C","D"), 30.0));
        

Run:

A total of 16 tests available to run
1. Unit Tests:
    1. When Cart Items Are Valid Then No Error
    2. When Cart Items Are Incorrect Then No Error
    3. testIsAvailableSingleItem
    4. testIsAvailableMultiItem
    5. When SinglePromotionType Applied then Validate Cart Value
    6. When SinglePromotionType Applied on Combo cart then Validate Cart Value
    7. When SinglePromotionType Applied on Combo cart and combo promotion then Validate Cart Value
    8. When MultiPromotionType Applied then Validate Cart Value
    9. When MultiPromotionType Applied on Combo cart then Validate Cart Value
    10. When MultiPromotionType Applied on Combo cart and combo promotion then Validate Cart Value
    11. When SinglePromotionType Not applied
    12. When MultiPromotionType Not applied
2. Functional Tests:
    1. Scenario A : 1*A = 50 ~ 1*B = 30 ~ 1*C = 20 :: Total=100
    2. Scenario B : 5*A = 130 + 2*50  ~ 5*B = 45 + 45 + 30 ~ 1*C = 20 :: Total=370
    3. Scenario C : 3*A = 130 ~ 5*B = 45 45 + 1*30 ~ 1*C 1*D = 30 :: Total=280
    4. Scenario D : MutualExclusive

