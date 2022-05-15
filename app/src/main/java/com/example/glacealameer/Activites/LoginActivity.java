package com.example.glacealameer.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.glacealameer.Fragment.DialogFragment;
import com.example.glacealameer.Model.Constants;
import com.example.glacealameer.Model.User;

import com.example.glacealameer.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseFirestore fb;
    FirebaseAuth auth;
    SharedPreferences sp;
    SharedPreferences.Editor edit;

    @Override
    protected void onStart() {
        super.onStart();
        if(auth != null && auth.getCurrentUser()!=null && sp.getString(Constants.USER_UID_KEY,null) != null&&!(sp.getBoolean(Constants.USER_IS_ADMIN_KEY,false))){
            moveToMainActivity();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        fb = FirebaseFirestore.getInstance();
        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        edit = sp.edit();


        binding.textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getBaseContext(),RegistrationActivity.class);
                startActivity(intent);
            }
        });

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!InternetConnection.checkConnection(LoginActivity.this)){
                    DialogFragment fragment=new DialogFragment();
                    fragment.show(getSupportFragmentManager(),null);
                    fragment.setCancelable(false);

                    return;
                }



                String email = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();

               if (TextUtils.isEmpty(email))
                    binding.etEmail.setError("Please enter your email");
                else if (TextUtils.isEmpty(password))
                    binding.etPassword.setError("Please enter your password");
                else {
                   binding.prog.setVisibility(View.VISIBLE);
                   binding.btLogin.setEnabled(false);
                   binding.textView5.setEnabled(false);
                    auth.signInWithEmailAndPassword(email, password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    if (authResult.getUser() != null)
                                        getUserDataFromFirestore(authResult.getUser().getUid());
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            binding.prog.setVisibility(View.GONE);
                            binding.btLogin.setEnabled(true);
                            binding.textView5.setEnabled(true);
                            Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }



    private void getUserDataFromFirestore(String uid) {
        fb.collection("Users").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {


                binding.prog.setVisibility(View.GONE);
                binding.btLogin.setEnabled(true);
                binding.textView5.setEnabled(true);

                if(documentSnapshot != null){
                    User user = documentSnapshot.toObject(User.class);

                    edit.putString(Constants.USER_UID_KEY,user.getUserID());
                    edit.putString(Constants.USER_EMAIL_KEY,user.getEmail());
                    edit.putString(Constants.USER_FULL_NAME_KEY,user.getFullName());
                    edit.putString(Constants.USER_IMAGE_KEY,user.getImage());
                    edit.commit();

                    if(user.isAdmin()){
                        Intent intent=new Intent(getBaseContext(),DashbordActivity.class);
                        startActivity(intent);
                    }
                    else {
                        moveToMainActivity();
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.prog.setVisibility(View.GONE);
                binding.btLogin.setEnabled(true);
                binding.textView5.setEnabled(true);
                Toast.makeText(LoginActivity.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveToMainActivity() {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}