package com.example.foodorderer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodorderer.api.DBHelper;
import com.example.foodorderer.model.Catalog;
import com.example.foodorderer.model.Dish;

public class MainActivity extends AppCompatActivity {
    private Catalog catalog;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new DBHelper(this);

        catalog = new Catalog(dbHelper);
        load(catalog.getDish(0),0);
    }
    public void switchToCart(View v)
    {
        startActivity(new Intent(getApplicationContext(), CartActivity.class));
    }

    public void switchToAdm(View v){
        startActivity(new Intent(getApplicationContext(), AdmActivity.class));
    }

    public void addToCart(View v){
        TextView pageView = findViewById(R.id.pageText);
        Integer index = Integer.parseInt(pageView.getText().toString())-1;
        Dish dish = catalog.getDish(index);

    }
    public void toPrev(View v){
        TextView pageView = findViewById(R.id.pageText);
        Integer index = Integer.parseInt(pageView.getText().toString())-2;
        Integer size = catalog.getSize();
        Dish dish = catalog.getDish(index);
        load(dish, index);
    }

    public void toNext(View v){
        TextView pageView = findViewById(R.id.pageText);
        Integer index = Integer.parseInt(pageView.getText().toString());
        Integer size = catalog.getSize();
        Dish dish = catalog.getDish(index);
        load(dish, index);
    }

    public void toCold(View v){
        Dish dish;
        dish = catalog.getDish("cold_dish");
        if (dish != null){
            load(dish,catalog.getIndex(dish));
        }
    }

    public void toDrink(View v){
        Dish dish;
        dish = catalog.getDish("drink");
        if (dish != null){
            load(dish,catalog.getIndex(dish));
        }
    }

    public void toDessert(View v){
        Dish dish;
        dish = catalog.getDish("dessert");
        if (dish != null){
            load(dish,catalog.getIndex(dish));
        }
    }

    public void toHot(View v){
        Dish dish;
        dish = catalog.getDish("hot_dish");
        if (dish != null){
            load(dish,catalog.getIndex(dish));
        }
    }
    public void load(Dish dish, Integer index) {
        Integer size = catalog.getSize();
        if (index==0){
            findViewById(R.id.previousButton).setEnabled(false);
        }else{
            findViewById(R.id.previousButton).setEnabled(true);
        }
        if (index+1==size){
            findViewById(R.id.nextButton).setEnabled(false);
        }else{
            findViewById(R.id.nextButton).setEnabled(true);
        }
        index++;
        TextView nameText = findViewById(R.id.dishNameText);
        nameText.setText(dish.getName());
        TextView groupText = findViewById(R.id.dishDescriptionText);
        groupText.setText(dish.getGroup());
        TextView pageText = findViewById(R.id.pageText);
        pageText.setText(index.toString());
        TextView descriptionText = findViewById(R.id.dishDescriptionText);
        descriptionText.setText(dish.getDescription());
    }
}