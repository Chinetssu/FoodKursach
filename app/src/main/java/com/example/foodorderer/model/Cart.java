package com.example.foodorderer.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Dish,Integer> listCart =new HashMap<>();
    public Integer addItem(Dish dish){
        if (listCart.containsKey(dish)){
            listCart.put(dish,listCart.get(dish)+1);
        }else{
            listCart.put(dish,1);
        }
        return listCart.get(dish);
    }
    public Dish outputItem(int i){
        return null;
    }
    public void deleteFromCart(Dish dish){
        Integer amount = listCart.get(dish);
        if (amount>1){
            listCart.put(dish,amount-1);
        }else{
            listCart.remove(dish);
        }
    }
}
