package com.example.foodorderer.api;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String  DATABASE_NAME = "dishDB";
    public static final String  TABLE_DISHES = "dishes";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_DISHES +
                "(name text primary key," +
                "group" + "text," +
                " description text)");

        db.execSQL("create table " + "recipe" +
                "(ingredient_name text," +
                "weight integer," +
                "dish_name text," +
                "CONSTRAINT new_pk PRIMARY KEY (ingredient_name, dish_name)," +
                "FOREIGN KEY(dish_name) REFERENCES dishes(name))");

        db.execSQL("create table " + "storage" +
                "(ingredient_name text primary key," +
                "weight integer," +
                "FOREIGN KEY(ingredient_name) REFERENCES recipe(ingredient_name))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    
}
