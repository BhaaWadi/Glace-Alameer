package com.example.glacealameer.Activites.Dashboard;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.glacealameer.Model.Order;
import com.example.glacealameer.Model.User;
import com.example.glacealameer.R;
import com.example.glacealameer.Views.CustomUserAdapter;
import com.example.glacealameer.databinding.ActivityUsersAllOrdersBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class UsersAllOrdersActivity extends AppCompatActivity {

    ActivityUsersAllOrdersBinding binding;
//    ArrayList<User> users;
//    ArrayList<Order> orders;
    FirebaseFirestore fb;
    ArrayList<String> users ;
    ArrayList<Order> orders ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsersAllOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fb = FirebaseFirestore.getInstance();
        getData();


    }

    private void getData() {


        fb.collection("Orders").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots!=null){
                    orders = (ArrayList<Order>) queryDocumentSnapshots.toObjects(Order.class);
                    CustomUserAdapter customUserAdapter = new CustomUserAdapter(orders, getBaseContext());
                    binding.rev.setAdapter(customUserAdapter);
                    binding.rev.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                    binding.rev.setHasFixedSize(true);
                }
                else   Toast.makeText(UsersAllOrdersActivity.this,"null", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UsersAllOrdersActivity.this,"fail", Toast.LENGTH_SHORT).show();

            }
        });

//        fb.collection("Orders").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//                if (queryDocumentSnapshots != null) {
//
//                    users = (ArrayList<User>) queryDocumentSnapshots.toObjects(User.class);
//                }
//
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//
//                Toast.makeText(UsersAllOrdersActivity.this, "Failure", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        fb.collection("Orders").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//
//                if (queryDocumentSnapshots != null) {
//                    Toast.makeText(UsersAllOrdersActivity.this, "okkkkkkkkkkkkk", Toast.LENGTH_SHORT).show();
//                    orders= (ArrayList<Order>) queryDocumentSnapshots.toObjects(Order.class);
//                }
//
//
//
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//
//                Toast.makeText(UsersAllOrdersActivity.this, "Failure", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        ArrayList<User> userOrder = new ArrayList<>();
//        for (int i = 0; i <users.size(); i++) {
//
//            if (orders.get(i).getUserId().equals(users.get(i).getUserID())) {
//                User user = users.get(i);
//                user.setTotal(orders.get(i).getTotal());
//                userOrder.add(user);
//            }}






    }

}
