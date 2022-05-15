package com.example.glacealameer.Activites.Dashboard;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.glacealameer.Model.Category;
import com.example.glacealameer.Model.Item;
import com.example.glacealameer.Model.Offer;
import com.example.glacealameer.R;
import com.example.glacealameer.Views.CategorySPAdapter;
import com.example.glacealameer.databinding.ActivityAddItemBinding;
import com.example.glacealameer.databinding.ActivityAddOffersBinding;
import com.example.glacealameer.databinding.ActivityOffersBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Calendar;

public class AddOffersActivity extends AppCompatActivity {
    private static final int PICK_IMG_REQ_CODE = 2;
    ActivityAddOffersBinding binding;
    FirebaseFirestore fb;
    FirebaseStorage storage;
    Uri selectedImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddOffersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fb = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        binding.mg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, PICK_IMG_REQ_CODE);

            }
        });

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.prog.setVisibility(View.VISIBLE);
                binding.save.setEnabled(false);
                binding.mg.setEnabled(false);

                String titel = binding.title.getText().toString();
                String subTitle = binding.subTitle.getText().toString();


                String docId = fb.collection("Offers").document().getId();

                Offer offer=new Offer(docId,titel,subTitle,"");
                uploadImageIntoFirebaseStorage(offer);
            }
        });
    }


    private void uploadImageIntoFirebaseStorage(Offer item) {
        Calendar calendar = Calendar.getInstance();
        storage.getReference().child("Offers/"+calendar.getTimeInMillis()).putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // another request to get image url
                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        item.setImage(uri.toString());
                        addItemToFirestore(item);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.prog.setVisibility(View.GONE);
                binding.save.setEnabled(true);
                binding.mg.setEnabled(true);
                Toast.makeText(AddOffersActivity.this, "Failed to upload item image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addItemToFirestore(Offer item) {
        fb.collection("Offers").document(item.getDocId()).set(item)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        binding.prog.setVisibility(View.GONE);
                        binding.save.setEnabled(true);
                        binding.mg.setEnabled(true);
                        Toast.makeText(AddOffersActivity.this, "Item added successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        binding.prog.setVisibility(View.GONE);
                        binding.save.setEnabled(true);
                        binding.mg.setEnabled(true);
                        Toast.makeText(AddOffersActivity.this, "Failed to add new item", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMG_REQ_CODE && resultCode == RESULT_OK && data != null) {
            Uri u = data.getData();
            int flag = data.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            getContentResolver().takePersistableUriPermission(u,flag);

            binding.mg.setImageURI(u);
            selectedImageUri = u;
        }
    }
}