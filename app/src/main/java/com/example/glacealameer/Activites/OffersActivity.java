package com.example.glacealameer.Activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.glacealameer.Model.News;
import com.example.glacealameer.Model.Offer;
import com.example.glacealameer.R;
import com.example.glacealameer.Views.NewsAdapter;
import com.example.glacealameer.Views.OffersAdapter;
import com.example.glacealameer.databinding.ActivityNewsBinding;
import com.example.glacealameer.databinding.ActivityOffersBinding;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OffersActivity extends AppCompatActivity {

    ActivityOffersBinding binding;

    FirebaseFirestore fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityOffersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fb=FirebaseFirestore.getInstance();
        fb.collection("Offers").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (!value.isEmpty()) {
                    ArrayList<Offer> items = (ArrayList<Offer>) value.toObjects(Offer.class);
                    populateDataIntoRV(items);
                }
            }
        });


    }
    private void populateDataIntoRV(List<Offer> items) {
        OffersAdapter adapter = new OffersAdapter(items, getBaseContext());

        binding.rvOffers.setAdapter(adapter);
        binding.rvOffers.setHasFixedSize(true);
        binding.rvOffers.setLayoutManager(new LinearLayoutManager(getBaseContext()));

    }
}