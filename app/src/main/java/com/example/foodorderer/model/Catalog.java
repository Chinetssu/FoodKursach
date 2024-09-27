package com.example.foodorderer.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.foodorderer.api.DBHelper;
import com.example.foodorderer.api.DBWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Catalog {
    ArrayList<Dish> listDish =new ArrayList<>();
    DBHelper dbHelper;
    public Catalog(DBHelper dbHelper) {
        this.addItem(new Dish("Pasta","hot_dish", "Макаронное изделие, свареное с любовью"));
        this.addItem(new Dish("Tea","drink", "Классика жанра, водичка на листочках"));
        this.addItem(new Dish("Milkshake","drink", "Молочный напиток"));
        this.addItem(new Dish("Brauni","dessert","Вкусный тортик"));
        this.dbHelper = dbHelper;

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(dbHelper.TABLE_DISHES, null, null, null, null, null, null);

        if (cursor.moveToFirst()){
            int nameIndex = cursor.getColumnIndex("name");
            int groupIndex = cursor.getColumnIndex("group");
            int descriptionIndex = cursor.getColumnIndex("description");
            do{
                String name = cursor.getString(nameIndex);
                String group = cursor.getString(groupIndex);
                String description = cursor.getString(descriptionIndex);
                Dish dish = new Dish(name, group, description);
                String[] selectionName = new String[] { name };
                Cursor ingCursor = database.query("recipe", null, "dish_name = ", selectionName,null,null,null);
                do{
                    dish.addIngridient(ingCursor.getString(1), ingCursor.getInt(2));
                }while (ingCursor.moveToNext());
                addItem(dish);
            }while (cursor.moveToNext());
        }

    }
    public void addItem(Dish dish){
        if (listDish.isEmpty()) {
            listDish.add(dish);
        }else{
            String group = dish.getGroup();
            Integer index = -1;
            switch (group){
                case ("cold_dish"):
                    listDish.add(0, dish);
                    break;
                case ("hot_dish"):
                    for (Dish litem: listDish) {
                        if (Objects.equals(litem.getGroup(), group) || Objects.equals(litem.getGroup(), "drink") || Objects.equals(litem.getGroup(), "dessert")){
                            index = listDish.indexOf(litem);
                            break;
                        }
                    }
                    if (index == -1){
                        listDish.add(dish);
                    }else{
                        listDish.add(index, dish);
                    }
                    break;
                case ("dessert"):
                    for (Dish litem: listDish) {
                        if (Objects.equals(litem.getGroup(), group) || Objects.equals(litem.getGroup(), "drink")){
                            index = listDish.indexOf(litem);
                            break;
                        }
                    }
                    if (index == -1){
                        listDish.add(dish);
                    }else{
                        listDish.add(index, dish);
                    }
                    break;
                case ("drink"):
                    for (Dish litem: listDish) {
                        if (Objects.equals(litem.getGroup(), group)){
                            index = listDish.indexOf(litem);
                            break;
                        }
                    }
                    if (index == -1){
                        listDish.add(dish);
                    }else{
                        listDish.add(index, dish);
                    }
                    break;
            }
        }
    }
    public void delItem(Dish dish){
        listDish.remove(dish);
    }
    public Dish getDish(int i){
        return listDish.get(i);
    }


    public Dish getDish(String group) {
        for (Dish dish :
                listDish) {
            if (Objects.equals(dish.getGroup(),group)){
                return dish;
            }
        }
        return null;
    }

    public Integer getIndex(Dish m) {
        return listDish.indexOf(m);
    }

    public Integer getSize (){
        return listDish.size();
    }
}
