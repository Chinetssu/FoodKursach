package com.example.foodorderer.api;

import java.sql.*;
import android.os.AsyncTask;

import com.example.foodorderer.model.Dish;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBWorker{
    //Class.forName("");
    public static final String PATH_TO_DB_FILE = "127.0.0.1:5432/my_dishes";
    public static final String URL = "jdbc:postgresql://" + PATH_TO_DB_FILE;
    public static Connection connection;



    public static void initDB(){
            try {
                connection = DriverManager.getConnection(URL, "postgres", "123");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static ResultSet loadCatalog(){
        ResultSet resultSet = null;
                try {
                        Statement statement = connection.createStatement();
                        resultSet = statement.executeQuery("SELECT * FROM dishes");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        return resultSet;
    }

    public static ResultSet loadItemIngredient(String iName){
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM recipe WHERE (dish_name = '"+iName+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void deleteDish(String name){
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("delete FROM dishes WHERE (name = '"+name+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addDish(String name, String group){
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("INSERT INTO dishes VALUES ('"+name+"', '"+group+"')");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
