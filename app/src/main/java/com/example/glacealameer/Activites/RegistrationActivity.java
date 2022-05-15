package com.example.glacealameer.Activites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.example.glacealameer.Fragment.DialogFragment;
import com.example.glacealameer.Model.Constants;
import com.example.glacealameer.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import   com.example.glacealameer.Model.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity {

    ActivityRegistrationBinding binding;
    private static final int PICK_IMG_REQ_CODE = 15;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseFirestore fb;
    Uri selectedImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        fb = FirebaseFirestore.getInstance();

        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, PICK_IMG_REQ_CODE);

            }
        });


        binding.rg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = binding.rgTextPersonName.getText().toString();
                String phone = binding.phone.getText().toString();
                String email = binding.editTextTextemail.getText().toString();
                String password = binding.editTextTextpassword.getText().toString();
                String confirmPassword = binding.confrimpassword.getText().toString();
                if(!InternetConnection.checkConnection(RegistrationActivity.this)){
                    DialogFragment fragment=new DialogFragment();
                    fragment.show(getSupportFragmentManager(),null);
                    fragment.setCancelable(false);
                    return;
                }
                else if (TextUtils.isEmpty(userName))
                    binding.rgTextPersonName.setError("Please enter your full name");

                 else if (TextUtils.isEmpty(email))
                    binding.editTextTextemail.setError("Please enter your email");
                else if (TextUtils.isEmpty(phone))
                    binding.phone.setError("Please enter your mobile");
                else if (TextUtils.isEmpty(password))
                    binding.editTextTextpassword.setError("Please enter your password");
                else if (TextUtils.isEmpty(confirmPassword))
                    binding.confrimpassword.setError("Please enter your RE-pass");
                else if (!password.equals(confirmPassword)) {
                    binding.editTextTextpassword.setError("Password does not match");
                    binding.confrimpassword.setError("Password does not match");
                }
                else if(selectedImageUri==null){

                     Snackbar.make(binding.textView2,"Please choose a picture",Snackbar.LENGTH_LONG).show();
                 }



                 else {
                    hideKeyboard(RegistrationActivity.this);

                    binding.prog.setVisibility(View.VISIBLE);
                    binding.rg.setEnabled(false);
                    binding.imageView.setEnabled(false);

                    auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            User user = new User(userName,email,phone,"",auth.getUid(),false);
                            uploadImageIntoFirebaseStorage(user);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            binding.prog.setVisibility(View.GONE);
                            binding.rg.setEnabled(true);
                            Toast.makeText(RegistrationActivity.this, "Failed to create user authentication", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                }

        });


    }




    private void uploadImageIntoFirebaseStorage(User user) {
        Calendar calendar=Calendar.getInstance();
        storage.getReference().child("src/"+calendar.getTimeInMillis()).putFile(selectedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // another request to get image url
                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        user.setImage(uri.toString());
                        addUserToFirestore(user);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.prog.setVisibility(View.GONE);
                binding.rg.setEnabled(true);
                binding.imageView.setEnabled(true);

                Toast.makeText(RegistrationActivity.this, "Failed to upload profile image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addUserToFirestore(User user) {

            fb.collection("Users").document(user.getUserID()).set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            binding.prog.setVisibility(View.GONE);
                            binding.rg.setEnabled(true);
                            binding.imageView.setEnabled(true);

                            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString(Constants.USER_UID_KEY, user.getUserID());
                            edit.putString(Constants.USER_EMAIL_KEY, user.getEmail());
                            edit.putString(Constants.USER_FULL_NAME_KEY, user.getFullName());
                            edit.putString(Constants.USER_IMAGE_KEY, user.getImage());
                            edit.putBoolean(Constants.USER_IS_ADMIN_KEY, user.isAdmin());
                            edit.apply();

                            Toast.makeText(RegistrationActivity.this, "User added successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    binding.prog.setVisibility(View.GONE);
                    binding.rg.setEnabled(true);
                    binding.imageView.setEnabled(true);

                    Toast.makeText(RegistrationActivity.this, "Failed to add user intp firestore", Toast.LENGTH_SHORT).show();
                }
            });


    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMG_REQ_CODE && resultCode == RESULT_OK && data != null) {
            Uri u = data.getData();
            int flag = data.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
           // getContentResolver().takePersistableUriPermission(u, flag);

            binding.imageView.setImageURI(u);
            selectedImageUri = u;
        }
    }
}
