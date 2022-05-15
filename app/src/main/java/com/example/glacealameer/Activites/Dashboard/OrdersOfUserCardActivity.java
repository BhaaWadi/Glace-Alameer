package com.example.glacealameer.Activites.Dashboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import com.example.glacealameer.Activites.BaseActivity;
import com.example.glacealameer.Model.Constants;
import com.example.glacealameer.Model.Order;
import com.example.glacealameer.Model.OrderItem;
import com.example.glacealameer.Model.User;
import com.example.glacealameer.R;
import com.example.glacealameer.Views.CartList2Adapter;
import com.example.glacealameer.Views.CartListAdapter;
import com.example.glacealameer.databinding.ActivityOrdersOfUserCardBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrdersOfUserCardActivity extends AppCompatActivity {

    ActivityOrdersOfUserCardBinding binding;

    FirebaseFirestore fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOrdersOfUserCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fb=FirebaseFirestore.getInstance();
        List<OrderItem> orderItems = (List<OrderItem>) getIntent().getSerializableExtra("userOrders");
        Order order = (Order) getIntent().getSerializableExtra("order");

        CartList2Adapter listAdapter=new CartList2Adapter(orderItems,getBaseContext());
        binding.rvOrderofUser.setAdapter(listAdapter);
        binding.rvOrderofUser.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        binding.rvOrderofUser.setHasFixedSize(true);
        binding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            fb.collection("Orders" ).document(order.getUserId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(OrdersOfUserCardActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();

                    listAdapter.getOrderItems().clear();
                    listAdapter.notifyDataSetChanged();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(OrdersOfUserCardActivity.this, "onFailure", Toast.LENGTH_SHORT).show();

                }
            });

            }
        });




//        String uid = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(Constants.USER_UID_KEY,null);



    }
}