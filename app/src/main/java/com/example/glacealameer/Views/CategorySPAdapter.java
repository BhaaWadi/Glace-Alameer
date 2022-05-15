package com.example.glacealameer.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.glacealameer.Model.Category;
import com.example.glacealameer.databinding.CustomCategoryItemBinding;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class CategorySPAdapter extends BaseAdapter {
    ArrayList<Category> categories;
    @Override
    public int getCount() {
        return categories.size();
    }

    public CategorySPAdapter(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public Category getItem(int i) {
        return categories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CustomCategoryItemBinding binding = CustomCategoryItemBinding.inflate(LayoutInflater.from(viewGroup.getContext()));

        Category c = categories.get(i);
        Picasso.get().load(c.getImage()).into(binding.customCategoryItemIv);
        binding.customCategoryItemTvName.setText(c.getName());
        return binding.getRoot();
    }
}
