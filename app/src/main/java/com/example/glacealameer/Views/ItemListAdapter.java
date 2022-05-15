package com.example.glacealameer.Views;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glacealameer.Model.Constants;
import com.example.glacealameer.Model.Favorite_1;
import com.example.glacealameer.Model.Item;
import com.example.glacealameer.R;
import com.example.glacealameer.databinding.CustomItemBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemHolder> {
    FirebaseFirestore fb;
    private List<Item> items;
    Context context;
    private OnItemClickListener listener;

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ItemListAdapter(List<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        fb = FirebaseFirestore.getInstance();
        CustomItemBinding binding = CustomItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Item item = items.get(position);
        holder.item = item;
        holder.binding.productName.setText(item.getName());
        holder.binding.SarProduct.setText(String.valueOf(item.getPrice()));
        Picasso.get().load(item.getImageUrl()).into(holder.binding.itemMg);
        String uid = PreferenceManager.getDefaultSharedPreferences(context).getString(Constants.USER_UID_KEY, null);
//
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        CustomItemBinding binding;
        Item item;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomItemBinding.bind(itemView);
            binding.cardd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);

                }
            });
//
        }
    }
}
