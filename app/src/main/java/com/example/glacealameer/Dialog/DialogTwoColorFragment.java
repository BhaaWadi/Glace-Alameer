package com.example.glacealameer.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.glacealameer.Activites.BaseActivity;
import com.example.glacealameer.Model.Item;
import com.example.glacealameer.Model.OrderItem;
import com.example.glacealameer.R;
import com.example.glacealameer.databinding.FragmentDialogBinding;
import com.example.glacealameer.databinding.FragmentDialogTwoColorBinding;

import org.jetbrains.annotations.NotNull;


public class DialogTwoColorFragment extends androidx.fragment.app.DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentDialogTwoColorBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DialogTwoColorFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         binding=FragmentDialogTwoColorBinding.inflate(inflater,container,false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String itemName= (String) binding.spOne.getSelectedItem();
                String itemName2= (String) binding.spTwo.getSelectedItem();

                //String price=binding.dialogConnectionCanecel.getText().toString();

                String fullName=itemName+"\n"+itemName2;
                Item item=new Item();
                item.setName(fullName);
                item.setPrice(15.0);
                OrderItem orderItem=new OrderItem(item,1,"","outSide Order");
                orderItem.setAddress(binding.address.getText().toString());
                BaseActivity.orderItems.add(orderItem);
                Toast.makeText(requireContext(), "done", Toast.LENGTH_LONG).show();

                dismiss();



            }
        });
    }


    public  interface OnDialogClick{

        void onConnectClick();


    }
}