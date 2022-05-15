package com.example.glacealameer.Activites;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.glacealameer.Model.Constants;
import com.example.glacealameer.Model.Order;
import com.example.glacealameer.Model.OrderItem;
import com.example.glacealameer.Views.CartListAdapter;
import com.example.glacealameer.databinding.ActivityCartBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class CartActivity extends BaseActivity {

    ActivityCartBinding binding;

    FirebaseFirestore fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fb=FirebaseFirestore.getInstance();
        double total = 0;
        for(OrderItem o: orderItems){
            total += o.getCount()*o.getItem().getPrice();
        }
        binding.cartTotalPrice.setText(String.valueOf(total));
        populateDataIntoRV(orderItems);

        binding.cartContinuePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String docId = fb.collection("Orders").document().getId();
                String uid = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString(Constants.USER_UID_KEY,null);
                Order o = new Order(docId,orderItems,uid);

                fb.collection("Orders").document(docId).set(o).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CartActivity.this, "Order added successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CartActivity.this, "Failed to add new order", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void populateDataIntoRV(List<OrderItem> orderItems) {
        CartListAdapter adapter = new CartListAdapter(orderItems,getBaseContext());
        binding.cartRv.setAdapter(adapter);
        binding.cartRv.setHasFixedSize(true);
        binding.cartRv.setLayoutManager(new LinearLayoutManager(this));

    }
}