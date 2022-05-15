package com.example.glacealameer.Views;

import android.widget.ImageView;

import com.example.glacealameer.Model.Item;


public interface OnItemClickListener {
   // void onItemClick(Item item, int count);
    void onItemClick(Item item);
    void onFavoriteClick(Item item, ImageView view);
}
