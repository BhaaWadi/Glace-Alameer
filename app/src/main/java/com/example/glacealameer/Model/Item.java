package com.example.glacealameer.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Item  implements Serializable {
    ArrayList<Item> favItems = new ArrayList<>();
    private Favorite_1 fav;
    private boolean isFavorite;
    private String docId;
    private String name;
    private String description;
    private double price;
    ArrayList<BollItem>bollItems;
    private String categoryName;
    private String imageUrl;

    public ArrayList<BollItem> getBollItems() {
        return bollItems;
    }

    public void setBollItems(ArrayList<BollItem> bollItems) {
        this.bollItems = bollItems;
    }

    public ArrayList<Item> getFavItems() {
        return favItems;
    }

    public void setFavItems(ArrayList<Item> favItems) {
        this.favItems = favItems;
    }

    public Item() {
    }

    public Favorite_1 getFav() {
        return fav;
    }

    public void setFav(Favorite_1 fav) {
        this.fav = fav;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Item(String docId, String name, String description, double price, String categoryName, String imageUrl) {
        this.docId = docId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryName = categoryName;
        this.imageUrl = imageUrl;
    }
}
