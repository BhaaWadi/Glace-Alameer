package com.example.glacealameer.Activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;


import com.example.glacealameer.Dialog.DialogAllColorFragment;
import com.example.glacealameer.Dialog.DialogOneColorFragment;
import com.example.glacealameer.Dialog.DialogThreeColorFragment;
import com.example.glacealameer.Dialog.DialogTwoColorFragment;
import com.example.glacealameer.Model.Item;
import com.example.glacealameer.R;
import com.example.glacealameer.Views.ItemListAdapter;
import com.example.glacealameer.databinding.ActivityOutsideBinding;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.example.glacealameer.R.drawable.ice_cream_famliy;

public class OutsideActivity extends AppCompatActivity {
    ActivityOutsideBinding binding;

    FirebaseFirestore fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOutsideBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fb=FirebaseFirestore.getInstance();


        binding.oneColorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogOneColorFragment fragment=new DialogOneColorFragment();
                fragment.show(getSupportFragmentManager(),null);

            }
        });

        binding.twoColorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogTwoColorFragment fragment=new DialogTwoColorFragment();
                fragment.show(getSupportFragmentManager(),null);
            }
        });

        binding.threeColorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogThreeColorFragment fragment=new DialogThreeColorFragment();
                fragment.show(getSupportFragmentManager(),null);
            }
        });

        binding.allColorCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAllColorFragment fragment=new DialogAllColorFragment();
                fragment.show(getSupportFragmentManager(),null);

            }
        });
    }


}