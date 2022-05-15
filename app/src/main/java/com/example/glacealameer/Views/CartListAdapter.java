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

import com.example.glacealameer.Dialog.DialogOneColorFragment;
import com.example.glacealameer.Model.Order;
import com.example.glacealameer.Model.OrderItem;
import com.example.glacealameer.R;
import com.example.glacealameer.databinding.CustomCartItemBinding;
import com.squareup.picasso.Picasso;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartHolder> {
    private List<OrderItem> orderItems;
    Context context ;
    double total = 0;
    MyListener myListener;
    public CartListAdapter(List<OrderItem> orderItems, Context context) {
        this.orderItems = orderItems;
        this.context = context;
    }

    public MyListener getMyListener() {
        return myListener;
    }

    public void setMyListener(MyListener myListener) {
        this.myListener = myListener;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CustomCartItemBinding binding = CustomCartItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CartHolder(binding.getRoot());
    }
    @Override
    public void onBindViewHolder(@NonNull  CartHolder holder, int position) {
        OrderItem orderItem = orderItems.get(position);



             if(!TextUtils.isEmpty(orderItem.getItem().getImageUrl())){
        Picasso.get().load(orderItem.getItem().getImageUrl()).into(holder.binding.customCartItemIv);}
          else if (orderItem.getOrderType().equals("outSide Order"))  {

               holder.binding.customCartItemIv.setImageDrawable(context.getResources().getDrawable(R.drawable.ice_cream_famliy));
             }
          else {
                 holder.binding.customCartItemIv.setImageDrawable(context.getResources().getDrawable(R.drawable.large));

             }

        holder.binding.customCartItemTvName.setText(orderItem.getItem().getName());
        holder.binding.customCartItemTvPrice.setText(String.valueOf(orderItem.getItem().getPrice()));
        holder.binding.customCartItemTvCount.setText(String.valueOf(orderItem.getCount()));
        holder.binding.customCartItemTvSubTotal.setText(String.valueOf(orderItem.getCount()*orderItem.getItem().getPrice()));

        for(OrderItem o: orderItems){
            total += o.getItem().getPrice();
        }
        holder.binding.mgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new  AlertDialog.Builder(context);
                builder.setMessage("Are you sure to Delete order")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                double totalm= orderItems.get(position).getCount()*orderItems.get(position).getItem().getPrice();
                                myListener.onCardClick(totalm);

                                orderItems.remove(orderItems.get(position));


                                notifyDataSetChanged();
                                Toast.makeText(context,"order deleted",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(context,"deleted canceled ",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle(" Delete order");
                alert.show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    class CartHolder extends RecyclerView.ViewHolder {
        CustomCartItemBinding binding;

        public CartHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomCartItemBinding.bind(itemView);
        }
    }

    public  interface MyListener{

        void  onCardClick(double itemtotal);
    }
}
