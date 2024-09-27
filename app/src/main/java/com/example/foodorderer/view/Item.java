package com.example.foodorderer.view;
import com.example.foodorderer.model.Dish;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
public class Item {
    private String name;
    private Dish dish;
    private String group;
    private Map<String,Integer> ingredients = new HashMap<String,Integer>();

    public Item(String name) {
        this.name = name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Dish getDish(){
        return dish;
    }
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }


    @Override
    public int hashCode(){
        return Objects.hashCode(name);
    }
}
