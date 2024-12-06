package com.example.paperdb;

import java.io.Serializable;
import java.util.UUID;

public class ClothingItem implements Serializable {
    private String id;
    private String name;
    private String description;
    private String imageUrl;
    private double price;

    // Конструктор по умолчанию
    public ClothingItem() {
        this.id = UUID.randomUUID().toString();
    }

    // Конструктор с параметрами
    public ClothingItem(String name, String description, String imageUrl, double price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    // Геттеры и сеттеры
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name;
    }
}

