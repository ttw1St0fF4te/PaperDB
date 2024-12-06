package com.example.paperdb;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.paperdb.Paper;

public class AddEditItemActivity extends AppCompatActivity {
    private EditText etName, etDescription, etPrice, etImageUrl;
    private Button btnSave, btnDelete;
    private ClothingItem item;
    private boolean isEditMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_item);

        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etPrice = findViewById(R.id.etPrice);
        etImageUrl = findViewById(R.id.etImageUrl);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        item = (ClothingItem) getIntent().getSerializableExtra("item");
        isEditMode = item != null;

        if (isEditMode) {
            etName.setText(item.getName());
            etDescription.setText(item.getDescription());
            etPrice.setText(String.valueOf(item.getPrice()));
            etImageUrl.setText(item.getImageUrl());
            btnDelete.setVisibility(Button.VISIBLE);
        }

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String description = etDescription.getText().toString();
            double price = Double.parseDouble(etPrice.getText().toString());
            String imageUrl = etImageUrl.getText().toString();

            if (item == null) {
                item = new ClothingItem();
                item.setId(UUID.randomUUID().toString());
            }
            item.setName(name);
            item.setDescription(description);
            item.setPrice(price);
            item.setImageUrl(imageUrl);

            List<ClothingItem> items = Paper.book().read("items", new ArrayList<ClothingItem>());
            boolean itemExists = false;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getId().equals(item.getId())) {
                    items.set(i, item);
                    itemExists = true;
                    break;
                }
            }
            if (!itemExists) {
                items.add(item);
            }
            Paper.book().write("items", items);

            finish();
        });

        btnDelete.setOnClickListener(v -> {
            List<ClothingItem> items = Paper.book().read("items", new ArrayList<ClothingItem>());
            items.removeIf(existingItem -> existingItem.getId().equals(item.getId()));
            Paper.book().write("items", items);
            Toast.makeText(AddEditItemActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
