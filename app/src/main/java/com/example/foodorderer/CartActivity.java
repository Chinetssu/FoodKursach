package com.example.foodorderer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderer.view.Item;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private final List<Item> items = new ArrayList<>();
    private final RecyclerView.Adapter adapter = new CartActivity.ItemAdapter(this.items);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    public void add(View view){
        this.items.add(new Item("Курица"));
        adapter.notifyItemInserted(this.items.size()-1);
    }

    public void delete(View view){

    }

    public void switchToCatalog(View v){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }


    private static final class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private final List<Item> items;

        public ItemAdapter(List<Item> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int index) {
            return new RecyclerView.ViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.items, parent, false)
            ) {
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
            TextView name = holder.itemView.findViewById(R.id.name);
            name.setText(this.items.get(index).getName());
            TextView indexText = holder.itemView.findViewById(R.id.indexText);
            indexText.setText(String.valueOf(index+1));
        }

        @Override
        public int getItemCount() {
            return this.items.size();
        }
    }
}