package com.example.glacealameer.Views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glacealameer.Model.Order;
import com.example.glacealameer.Model.OrderItem;
import com.example.glacealameer.R;
import com.example.glacealameer.databinding.CustomCartItemAdminBinding;
import com.example.glacealameer.databinding.CustomCartItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartList2Adapter extends RecyclerView.Adapter<CartList2Adapter.CartHolder> {
    private List<OrderItem> orderItems;
    Context context ;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public CartList2Adapter(List<OrderItem> orderItems, Context context) {
        this.orderItems = orderItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomCartItemAdminBinding binding = CustomCartItemAdminBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CartHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        OrderItem orderItem = orderItems.get(position);


             if(!TextUtils.isEmpty(orderItem.getItem().getImageUrl())){
        Picasso.get().load(orderItem.getItem().getImageUrl()).into(holder.binding.customCartItemIv);}
          else if( orderItem.getOrderType().equals("outSide Order"))  {

               holder.binding.customCartItemIv.setImageDrawable(context.getResources().getDrawable(R.drawable.ice_cream_famliy));
             }
          else {
                 holder.binding.customCartItemIv.setImageDrawable(context.getResources().getDrawable(R.drawable.large));

             }

        holder.binding.customCartItemTvName.setText(orderItem.getItem().getName());
        holder.binding.customCartItemTvPrice.setText(String.valueOf(orderItem.getItem().getPrice()));
        holder.binding.customCartItemTvCount.setText(String.valueOf(orderItem.getCount()));
        holder.binding.customCartItemTvSubTotal.setText(String.valueOf(orderItem.getCount()*orderItem.getItem().getPrice()));






    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    class CartHolder extends RecyclerView.ViewHolder {
        CustomCartItemAdminBinding binding;

        public CartHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomCartItemAdminBinding.bind(itemView);
        }
    }
}
