package com.example.glacealameer.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.glacealameer.Database.DatabaseFavorite;
import com.example.glacealameer.Model.BollItem;
import com.example.glacealameer.Model.Item;
import com.example.glacealameer.Model.OrderItem;
import com.example.glacealameer.R;
import com.example.glacealameer.Views.CustomBollAdapter;
import com.example.glacealameer.databinding.ActivityDetailsInsideBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DetailsInsideActivity extends BaseActivity {

    ActivityDetailsInsideBinding binding;

    FirebaseFirestore fb;
    String iceCount;
    DatabaseFavorite database ;
    boolean isFavorite;
    int value;

    ArrayList<BollItem>listBoll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailsInsideBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailsInsideActivity.this, "Order Sent", Toast.LENGTH_SHORT).show();
            }
        });




        fb=FirebaseFirestore.getInstance();
        listBoll=new ArrayList<>();
       int id=    getIntent().getIntExtra("id",0);
         if(id>=1&&id<=4) {

             switch (id){
                 case 1:
                     binding.iceCreamType.setText("Small");
                     binding.bollsPrice.setText("2");
                     binding.Boll2.setVisibility(View.GONE);
                     binding.resBoll2.setVisibility(View.GONE);
                     binding.mgBoll2.setVisibility(View.GONE);
                     binding.Boll3.setVisibility(View.GONE);
                     binding.mgBoll3.setVisibility(View.GONE);
                     binding.wafell.setImageResource(R.drawable.w_4);
                     binding.resBoll3.setVisibility(View.GONE);
                     binding.Boll4.setVisibility(View.GONE);
                     binding.mgBoll4.setVisibility(View.GONE);
                     binding.resBoll4.setVisibility(View.GONE);
                     break;

                 case 2:
                     binding.iceCreamType.setText("medium");
                     binding.wafell.setImageResource(R.drawable.w_4);
                     binding.bollsPrice.setText("3");
                     binding.mgBoll3.setVisibility(View.GONE);
                     binding.mgBoll4.setVisibility(View.GONE);
                     binding.Boll3.setVisibility(View.GONE);
                     binding.resBoll3.setVisibility(View.GONE);
                     binding.Boll4.setVisibility(View.GONE);
                     binding.resBoll4.setVisibility(View.GONE);
                     break;

                 case 3:
                     binding.iceCreamType.setText("Larg");
                     binding.wafell.setImageResource(R.drawable.w_4);
                     binding.bollsPrice.setText("5");
                     binding.mgBoll4.setVisibility(View.GONE);
                     binding.Boll4.setVisibility(View.GONE);
                     binding.resBoll4.setVisibility(View.GONE);
                     break;

                 case 4:
                     binding.iceCreamType.setText("XlLarg");
                     binding.wafell.setImageResource(R.drawable.w_4);
                     binding.bollsPrice.setText("7");

                     break;



             }

             fb.collection("Boll Item").whereEqualTo("bollType", "Classic").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                 @Override
                 public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                     if (queryDocumentSnapshots != null) {
                         ArrayList<BollItem> bollItems = (ArrayList<BollItem>) queryDocumentSnapshots.toObjects(BollItem.class);
                         populateDataIntoRV(bollItems);


                     }

                 }
             }).addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull @NotNull Exception e) {

                     Toast.makeText(DetailsInsideActivity.this, "FailureFailure", Toast.LENGTH_SHORT).show();

                 }
             });

         }

        else  {

            switch (id){
                case 5:
                    binding.iceCreamType.setText("Special Small");
                    binding.wafell.setImageResource(R.drawable.w_4);
                    binding.bollsPrice.setText("5");
                    binding.Boll3.setVisibility(View.GONE);
                    binding.mgBoll3.setVisibility(View.GONE);
                    binding.resBoll3.setVisibility(View.GONE);
                    binding.Boll4.setVisibility(View.GONE);
                    binding.mgBoll4.setVisibility(View.GONE);
                    binding.resBoll4.setVisibility(View.GONE);
                    break;

                case 6:
                    binding.iceCreamType.setText("Special medium");
                    binding.wafell.setImageResource(R.drawable.w_4);
                    binding.bollsPrice.setText("7");
                    binding.mgBoll4.setVisibility(View.GONE);
                    binding.Boll4.setVisibility(View.GONE);
                    binding.resBoll4.setVisibility(View.GONE);
                    break;

                case  7:
                    binding.iceCreamType.setText("Special Larg");
                    binding.wafell.setImageResource(R.drawable.w_4);
                    binding.bollsPrice.setText("9");



            }

            fb.collection("Boll Item").whereEqualTo("bollType", "Special").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                    if (queryDocumentSnapshots != null) {
                        ArrayList<BollItem> bollItems = (ArrayList<BollItem>) queryDocumentSnapshots.toObjects(BollItem.class);
                        populateDataIntoRV(bollItems);


                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {

                    Toast.makeText(DetailsInsideActivity.this, "FailureFailure", Toast.LENGTH_SHORT).show();

                }
            });

        }


        int price=  Integer.parseInt(binding.bollsPrice.getText().toString() )  ;
        binding.mgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String text_result = binding.result.getText().toString();

                value=Integer.parseInt(text_result);
                value +=1;

                int priceTotal=price*value;
                binding.bollsPrice.setText(priceTotal+"");
                binding.result.setText(String.valueOf(value));
            }
        });
        binding.mgMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_result = binding.result.getText().toString();

                 value=Integer.parseInt(text_result);
                if(value>1){
                    value -=1;

                }

                int priceTotal=price*value;
                binding.bollsPrice.setText(priceTotal+"");
                binding.result.setText(String.valueOf(value));
            }
        });

        binding.addOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String docId=fb.collection("AllOrders").document().getId();
           String  iceCreamType=  binding.iceCreamType.getText().toString();
           double price=Double.parseDouble(binding.bollsPrice.getText().toString());

            String name = "";
                for(BollItem bollItem:listBoll){

                name=bollItem.getBollName()+"\n";

                }
                 Item item=new Item("",iceCreamType,"",price,"Ice Cream","");
                 item.setBollItems(listBoll);
                 item.setName(name);
                 int count=Integer.parseInt(binding.result.getText().toString());
                OrderItem  orderItem=new OrderItem(item,count,"","inside Order");
                //String docId=fb.collection("AllOrders").document().getId();
                orderItems.add(orderItem);

            }
        });

    }

    private void populateDataIntoRV(ArrayList<BollItem> bollItems) {



        //////////////adapter
        CustomBollAdapter adapter=new CustomBollAdapter(bollItems);

        binding.resBoll1.setAdapter(adapter);
        binding.resBoll1.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL ,false));
        binding.resBoll1.setHasFixedSize(true);
        adapter.setListener(new CustomBollAdapter.OnBollItemClickListener() {
            @Override
            public void onItemClick(BollItem item) {
                Picasso.get().load(item.getImgURl()).into(binding.mgBoll1);
                listBoll.add(item);

            }
        });
        //////////////////////
        CustomBollAdapter adapter2=new CustomBollAdapter(bollItems);
        binding.resBoll2.setAdapter(adapter2);
        binding.resBoll2.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL ,false));
        binding.resBoll2.setHasFixedSize(true);
        adapter2.setListener(new CustomBollAdapter.OnBollItemClickListener() {
            @Override
            public void onItemClick(BollItem item) {
                Picasso.get().load(item.getImgURl()).into(binding.mgBoll2);
                listBoll.add(item);

            }
        });

        ////////////////
        CustomBollAdapter adapter3=new CustomBollAdapter(bollItems);
        binding.resBoll3.setAdapter(adapter3);
        binding.resBoll3.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL ,false));
        binding.resBoll3.setHasFixedSize(true);
        adapter3.setListener(new CustomBollAdapter.OnBollItemClickListener() {
            @Override
            public void onItemClick(BollItem item) {
                Picasso.get().load(item.getImgURl()).into(binding.mgBoll3);
                listBoll.add(item);

            }
        });
        ////////////////////
        CustomBollAdapter adapter4=new CustomBollAdapter(bollItems);
        binding.resBoll4.setAdapter(adapter4);
        binding.resBoll4.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL ,false));
        binding.resBoll4.setHasFixedSize(true);
        adapter4.setListener(new CustomBollAdapter.OnBollItemClickListener() {
            @Override
            public void onItemClick(BollItem item) {
                Picasso.get().load(item.getImgURl()).into(binding.mgBoll4);
                listBoll.add(item);

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cart:
                Intent cartIntent = new Intent(getBaseContext(), CartActivity.class);
                startActivity(cartIntent);
                return true;
            default:
                return false;
        }
    }

}