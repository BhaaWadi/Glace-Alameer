package com.example.glacealameer.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.glacealameer.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


      // getWindow().setStatusBarColor(getResources().getColor(R.color.brown));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent intent=new Intent(getBaseContext(),LoginActivity.class);

                startActivity(intent);


            }
        },3000);



    }
}