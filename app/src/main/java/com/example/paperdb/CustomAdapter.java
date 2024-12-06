package com.example.paperdb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<ClothingItem> {
    private Context context;
    private List<ClothingItem> items;

    public CustomAdapter(Context context, List<ClothingItem> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ClothingItem item = items.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_clothing, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvName);
        ImageView ivImage = convertView.findViewById(R.id.ivImage);

        tvName.setText(item.getName());
        Picasso.get().load(item.getImageUrl()).into(ivImage);

        return convertView;
    }
}

