package com.example.glacealameer.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.glacealameer.Activites.Dashboard.AddBollsActivity;
import com.example.glacealameer.Activites.Dashboard.AddCategoriesActivity;
import com.example.glacealameer.Activites.Dashboard.AddItemActivity;
import com.example.glacealameer.Activites.Dashboard.AddNewsActivity;
import com.example.glacealameer.Activites.Dashboard.AddOffersActivity;
import com.example.glacealameer.Activites.Dashboard.SendNotificationsActivity;
import com.example.glacealameer.Activites.Dashboard.UsersAllOrdersActivity;
import com.example.glacealameer.databinding.ActivityDashbordBinding;

public class DashbordActivity extends AppCompatActivity {
    ActivityDashbordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDashbordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.carAddBolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getBaseContext(), AddBollsActivity.class);
                startActivity(intent);
            }
        });

        binding.cardAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getBaseContext(), AddItemActivity.class);
                startActivity(intent);
            }
        });

        binding.cardNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getBaseContext(), AddNewsActivity.class);
                startActivity(intent);
            }
        });
        binding.cardAddCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getBaseContext(), AddCategoriesActivity.class);
                startActivity(intent);
            }
        });
        binding.cardNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getBaseContext(), SendNotificationsActivity.class);
                startActivity(intent);
            }
        });
        binding.offersCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getBaseContext(), AddOffersActivity.class);
                startActivity(intent);
            }
        });
        binding.caredUserOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getBaseContext(), UsersAllOrdersActivity.class);
                startActivity(intent);
            }
        });
    }
}