package com.example.glacealameer.Model;

import java.util.ArrayList;

public class Favorite_1 {
    String userId;
    ArrayList<Item> items;

    public Favorite_1(String userId, ArrayList<Item> items) {
        this.userId = userId;
        this.items = items;
    }

    public Favorite_1() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
