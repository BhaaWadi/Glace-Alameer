package com.example.glacealameer.ui.Cart;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.glacealameer.Activites.BaseActivity;
import com.example.glacealameer.Model.Constants;
import com.example.glacealameer.Model.Order;
import com.example.glacealameer.Model.OrderItem;
import com.example.glacealameer.Views.CartListAdapter;
import com.example.glacealameer.databinding.FragmentNotificationsBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    double total = 0.0;
    ArrayList<Order> orders;
    ArrayList<OrderItem> orderItems;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        FirebaseFirestore fb=FirebaseFirestore.getInstance();


        orderItems= BaseActivity.orderItems;

        if(orderItems!=null) {
            for (OrderItem o : orderItems) {
                total += o.getCount() * o.getItem().getPrice();
            }
        }
        binding.cartTotalPrice.setText(String.valueOf(total));
        CartListAdapter listAdapter=new CartListAdapter(orderItems,getContext());
        listAdapter.setMyListener(new CartListAdapter.MyListener() {
            @Override
            public void onCardClick(double itemtotal) {

                double result=total-itemtotal;
                total=result;
                binding.cartTotalPrice.setText(String.valueOf(result));


            }
        });

        binding.cartRv.setAdapter(listAdapter);
        binding.cartRv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.cartRv.setHasFixedSize(true);

       fb.collection("Orders").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
           @Override
           public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

               if(queryDocumentSnapshots!=null){
                   orders= (ArrayList<Order>) queryDocumentSnapshots.toObjects(Order.class);

               }

           }
       });

        binding.cartContinuePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uid = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(Constants.USER_UID_KEY,null);


                for(int i=0;i<orders.size();i++){

                    if(uid.equals(orders.get(i).getUserId())){

                   ArrayList<OrderItem> orderItem= (ArrayList<OrderItem>) orders.get(i).getOrderItems();

                   for(int d=0;d<orderItem.size();d++){

                       if (orderItems != null) {
                           orderItems.add(orderItem.get(d));
                       }
                   }

                    }


                }




                if(orderItems!=null) {
                    for (OrderItem o : orderItems) {
                        total += o.getCount() * o.getItem().getPrice();
                    }
                }
                Order order1=new Order(uid,orderItems,uid);
                order1.setTotal(total);
                fb.collection("Orders").document(uid).set(order1).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getActivity(), "Order added successfully", Toast.LENGTH_SHORT).show();

                    }
                });



                orderItems.clear();
                binding.cartTotalPrice.setText("0");
                listAdapter.notifyDataSetChanged();
                goToPayment(total);

            }
        });

        return root;
    }


    void  goToPayment(double total){


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}