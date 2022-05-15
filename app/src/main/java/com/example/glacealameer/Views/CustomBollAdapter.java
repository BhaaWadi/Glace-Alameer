package com.example.glacealameer.Views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glacealameer.Model.BollItem;
import com.example.glacealameer.Model.Item;
import com.example.glacealameer.databinding.RowBollBinding;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomBollAdapter extends RecyclerView.Adapter<CustomBollAdapter.BollHolder> {


    ArrayList<BollItem> items ;

    OnBollItemClickListener  listener;

    public OnBollItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnBollItemClickListener listener) {
        this.listener = listener;
    }

    public ArrayList<BollItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<BollItem> items) {
        this.items = items;
    }

    public CustomBollAdapter(ArrayList<BollItem> items) {
        this.items = items;
    }

    @NonNull
    @NotNull
    @Override
    public BollHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        RowBollBinding bollBinding = RowBollBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BollHolder(bollBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull BollHolder holder, int position) {
        holder.binding.nameBoll.setText(items.get(position).getBollName());
        Picasso.get().load(items.get(position).getImgURl()).into(holder.binding.ngRowBoll);
        BollItem bollItem=items.get(position);


        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(bollItem);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class BollHolder extends RecyclerView.ViewHolder {

        RowBollBinding binding;

        public BollHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            binding = RowBollBinding.bind(itemView);
        }
    }

    public  interface OnBollItemClickListener{

        void onItemClick(BollItem item);

    }



}
