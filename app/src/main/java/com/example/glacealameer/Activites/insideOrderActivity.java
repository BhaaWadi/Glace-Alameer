package com.example.glacealameer.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.glacealameer.Model.Category;
import com.example.glacealameer.Model.Item;
import com.example.glacealameer.Views.OnFragmentInteractionListener;
import com.example.glacealameer.Views.PagerAdapter;
import com.example.glacealameer.databinding.ActivityInsideOrderBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class insideOrderActivity extends FragmentActivity implements OnFragmentInteractionListener {


    ActivityInsideOrderBinding binding;
    FirebaseFirestore fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityInsideOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       fb = FirebaseFirestore.getInstance();
        getCategoriesFromFB();




    }

    private void getCategoriesFromFB() {

        fb.collection("Category").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    ArrayList<Category> categories = (ArrayList<Category>) queryDocumentSnapshots.toObjects(Category.class);
                    setupViewPagerWithTabs(categories);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getBaseContext(), "Failed to red categories data", Toast.LENGTH_SHORT).show();
            }
        });

        binding.cart12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),CartActivity.class);
                startActivity(intent);
            }
        });



    }

    private void setupViewPagerWithTabs(ArrayList<Category> categories) {

        PagerAdapter adapter = new PagerAdapter(this, categories);
        binding.pager.setAdapter(adapter);
        new TabLayoutMediator(binding.tabLayout, binding.pager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(categories.get(position).getName());
            }
        }).attach();
    }




    @Override
    public void onItemSend(Item item, int value) {
        Intent  intent=new Intent(getBaseContext(),DetailsActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);
    }
}