package com.example.glacealameer.Views;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.glacealameer.Fragment.IceCreamFragment;
import com.example.glacealameer.Fragment.IceDrinkFragment;
import  com.example.glacealameer.Model.Category;

import java.util.List;

public class PagerAdapter extends FragmentStateAdapter {
    List<Category> categories;
    public PagerAdapter(@NonNull FragmentActivity fragmentActivity, List<Category> categories) {
        super(fragmentActivity);
        this.categories = categories;

    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String catName = categories.get(position).getName();

        if(position==3){
            return  new IceCreamFragment();
        }
        else
        return IceDrinkFragment.newInstance(catName);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
