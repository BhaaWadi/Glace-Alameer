package com.example.glacealameer.Activites;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.glacealameer.Model.Item;
import com.example.glacealameer.Model.News;
import com.example.glacealameer.R;
import com.example.glacealameer.Views.ItemListAdapter;
import com.example.glacealameer.Views.NewsAdapter;
import com.example.glacealameer.Views.OnItemClickListener;
import com.example.glacealameer.databinding.ActivityNewsBinding;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    ActivityNewsBinding binding;

    FirebaseFirestore fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fb=FirebaseFirestore.getInstance();
        fb.collection("News").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (!value.isEmpty()) {
                    ArrayList<News> items = (ArrayList<News>) value.toObjects(News.class);
                    populateDataIntoRV(items);
                }
            }
        });

    }
    private void populateDataIntoRV(List<News> items) {
        NewsAdapter adapter = new NewsAdapter(items, getBaseContext());

        binding.rvNews.setAdapter(adapter);
        binding.rvNews.setHasFixedSize(true);
        binding.rvNews.setLayoutManager(new LinearLayoutManager(getBaseContext()));

    }

}