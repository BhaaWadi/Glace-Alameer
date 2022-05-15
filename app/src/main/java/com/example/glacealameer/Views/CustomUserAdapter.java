package com.example.glacealameer.Views;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glacealameer.Activites.Dashboard.OrdersOfUserCardActivity;
import com.example.glacealameer.Model.Constants;
import com.example.glacealameer.Model.Item;
import com.example.glacealameer.Model.Order;
import com.example.glacealameer.Model.OrderItem;
import com.example.glacealameer.Model.User;
import com.example.glacealameer.databinding.CustomItemBinding;
import com.example.glacealameer.databinding.CustomItemUsersNameAdminCartBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

;

public class CustomUserAdapter extends RecyclerView.Adapter<CustomUserAdapter.ItemHolder> {
    FirebaseFirestore fb;
    private List<Order> orders;
    Context context;
    private OnItemClickListener listener;
    String resultTotal;

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public CustomUserAdapter(List<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        fb = FirebaseFirestore.getInstance();
        CustomItemUsersNameAdminCartBinding binding = CustomItemUsersNameAdminCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Order order = orders.get(position);
        User user;
        holder.order=order;
        holder.orderItems = order.getOrderItems();

        fb.collection("Users").document(order.getUserId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                holder.binding.text.setText(user.getFullName());
               Picasso.get().load(user.getImage()).into(holder.binding.imageView);
               holder.binding.total.setText(String.valueOf(order.getTotal()));




            }
        });


//        fb.collection("Orders").whereEqualTo("userId",item.getUserID()).addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                if(!value.isEmpty()){
//
//               ArrayList<Order>orders= (ArrayList<Order>) value.toObjects(Order.class);
//
//               for(int i=0;i<orders.size();i++){
//
//                   resultTotal +=orders.get(i).getTotal();
//
//
//               }
//
//                    //orders.get()
//                }
//
//            }
//        });

//
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        CustomItemUsersNameAdminCartBinding binding;
        Order order ;
        List<OrderItem> orderItems;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            binding = CustomItemUsersNameAdminCartBinding.bind(itemView);

            binding.cared.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent=new Intent(context, OrdersOfUserCardActivity.class);
                    intent.putExtra("userOrders", (Serializable) orderItems);
                    intent.putExtra("order", order);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    context.startActivity(intent);
                }
            });

//
        }
    }
}
