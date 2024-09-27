package com.example.foodorderer.model;

import java.util.HashMap;
import java.util.Map;
public class Dish {
    private String name;
    private String group;
    private String description;
    private Map<String,Integer> ingredients = new HashMap<String,Integer>();
    public Dish() {
        this.name = "Карбонара";
        this.group = "hot_dish";
        this.ingredients.put("ham",200);
        this.ingredients.put("dough",300);
        this.ingredients.put("salt",15);
    }
    public Dish(String name, String group, String description) {
        this.name = name;
        this.group = group;
        this.description = description;
    }
    public void addIngridient(String ingridient, Integer weight){
        this.ingredients.put(ingridient,weight);
    }
    public void removeIngridient(String ingridient){
        this.ingredients.remove(ingridient);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }
}
