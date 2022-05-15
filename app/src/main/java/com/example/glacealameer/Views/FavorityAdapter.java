package com.example.glacealameer.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glacealameer.Model.Item;
import com.example.glacealameer.databinding.CustomItemFavarityBinding;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FavorityAdapter extends RecyclerView.Adapter<FavorityAdapter.FavorityHolder> {
    ArrayList<Item> items;
    Context context;
    OnFavoriteClickListener listener;

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public FavorityAdapter(ArrayList<Item> items, Context context, OnFavoriteClickListener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;
    }

    public OnFavoriteClickListener getListener() {
        return listener;
    }

    public void setListener(OnFavoriteClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public FavorityHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        CustomItemFavarityBinding binding = CustomItemFavarityBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FavorityHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FavorityHolder holder, int position) {
        Item item = items.get(position);
        holder.binding.itemFavortyTvName.setText(item.getName());
        holder.binding.itemFavortyTvPraise.setText(String.valueOf(item.getPrice()));
//        holder.binding.itemFavortyIvvv.setImageURI(Uri.parse(item.getImageUrl()));
            Picasso.get().load(item.getImageUrl()).into(holder.binding.aaaaaa);

        holder.binding.favoriteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFavoriteClick(item.getDocId());

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class FavorityHolder extends RecyclerView.ViewHolder{
        CustomItemFavarityBinding binding;




        public FavorityHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            binding=CustomItemFavarityBinding.bind(itemView);
        }
    }
}
