package com.example.glacealameer.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.glacealameer.Database.DatabaseFavorite;
import com.example.glacealameer.Model.Constants;
import com.example.glacealameer.Model.Item;
import com.example.glacealameer.Model.OrderItem;
import com.example.glacealameer.R;
import com.example.glacealameer.Views.OnItemClickListener;
import com.example.glacealameer.databinding.ActivityDetailsBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    FirebaseFirestore fb;
    int result=1;
    boolean isFavorite;
    DatabaseFavorite database ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         fb=FirebaseFirestore.getInstance();
        Item item = (Item) getIntent().getSerializableExtra("item");
        database=new DatabaseFavorite(this);
        isFavorite = database.searchItem(item.getDocId());

        if (isFavorite){
            binding.deFavorite.setImageResource(R.drawable.ic_save_on);
        }
        else if (!isFavorite){
            binding.deFavorite.setImageResource(R.drawable.ic_save_off);
        }

        Picasso.get()
                .load(item.getImageUrl())
                .into(binding.mg);
        binding.pprductName.setText(item.getName());
        binding.pprductPice.setText(String.valueOf(item.getPrice()));
        binding.productDecp.setText(item.getDescription());

        binding.mgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_result = binding.result.getText().toString();
                 result = Integer.parseInt(text_result);
                result += 1;
                binding.pprductPice.setText(String.valueOf(item.getPrice()*result));
                binding.result.setText(result+"");
            }
        });
        binding.mgMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_result = binding.result.getText().toString();
                 result = Integer.parseInt(text_result);
                if (result > 1  ) {
                    result -= 1;
                }
                binding.pprductPice.setText(String.valueOf(item.getPrice()*result));
                binding.result.setText(result+"");


            }
        });
        binding.deFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( database.insertItem(item)){
                    Toast.makeText(DetailsActivity.this, "تم الاضافة الى المفضلة", Toast.LENGTH_LONG).show();
                }
                else if (! database.insertItem(item)){
                }
                if (isFavorite){
                    binding.deFavorite.setImageResource(R.drawable.ic_save_off);
                    database.deleteFurniture(item.getDocId());

                   Toast.makeText(DetailsActivity.this, "تم ازالة العنصر الى المفضله", Toast.LENGTH_SHORT).show();

                    isFavorite= false;

                }
                else if (!isFavorite){
                    binding.deFavorite.setImageResource(R.drawable.ic_save_on);
                    if (database.insertItem(item)){
                        Toast.makeText(DetailsActivity.this, "added", Toast.LENGTH_SHORT).show();
                    }
                    isFavorite= true;

                }
            }
        });




        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String productName=binding.pprductName.getText().toString();
                String productPrice=binding.pprductPice.getText().toString();
                //String result=binding.result.getText().toString();
                String tableNumber=binding.tableNumber.getText().toString();
                SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String uid= sp.getString(Constants.USER_UID_KEY,null);
                String UserName=sp.getString(Constants.USER_FULL_NAME_KEY,null);

             //   String docId=fb.collection("AllOrders").document().getId();
                OrderItem orderItem=new OrderItem(item,result,"","insideOrder");
                BaseActivity.orderItems.add(orderItem);
                Toast.makeText(DetailsActivity.this, "ok order", Toast.LENGTH_SHORT).show();



            }
        });

    }

//    @Override
//    public void onItemClick(Item item) {
//        Picasso.get()
//                .load(item.getImageUrl())
//                .into(binding.mg);
//        binding.pprductName.setText(item.getName());
//        binding.pprductPice.setText(String.valueOf(item.getPrice()));
//    }
//
//    @Override
//    public void onFavoriteClick(Item item, ImageView view) {
//
//    }
}