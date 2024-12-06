package com.example.paperdb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private ListView listViewItems;
    private Button btnAddItem;
    private CustomAdapter adapter;
    private List<ClothingItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewItems = findViewById(R.id.listViewItems);
        btnAddItem = findViewById(R.id.btnAddItem);

        items = Paper.book().read("items", new ArrayList<ClothingItem>());
        adapter = new CustomAdapter(this, items);
        listViewItems.setAdapter(adapter);

        btnAddItem.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditItemActivity.class);
            startActivity(intent);
        });

        listViewItems.setOnItemClickListener((parent, view, position, id) -> {
            ClothingItem item = items.get(position);
            Intent intent = new Intent(MainActivity.this, AddEditItemActivity.class);
            intent.putExtra("item", item);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        items = Paper.book().read("items", new ArrayList<ClothingItem>());
        adapter.clear();
        adapter.addAll(items);
        adapter.notifyDataSetChanged();
    }
}
