package com.example.loginsignuprealtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    
    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginBotton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);
        
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword() {
                    
                } else {
                    checkUser();
                }
            }
        });
                
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
                
    }
            
    public Boolean validateUsername() {
        String va1 = loginUsername.getText().toString();
        if (va1.isEmpty() ) {
            loginUsername.setError("Username cannot be Empty");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }
            
    public Boolean validatePassword() {
        String va1 = loginPassword.getText().toString();
        if (va1.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }
            
            
    public void checkUser() {
        String userUsername = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();
        
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
        
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                
                if (snapshot.exists()) {
                    
                    loginUsername.setError(null);
                    String passwordFromDB =
                        snapshot.child(userUsername).child("password").getValue(String.class);
                    
                    if (passwordFromDB.equals(userPassword)) {
                        loginUsername.setError(null);
                        
                        String nameFromDB =
                            snapshot.child(userUsername).child("name").getValue(String.class);
                        String emailFromDB =
                            snapshot.child(userUsername).child("email").getValue(String.class);
                        String usernameFromDB =
                            snapshot.child(userUsername).child("username").getValue(String.class);
                        
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB);
                        
                        startActivity(intent);
                    } else {
                    loginPassword.setError("Invalid Credentials");
                    loginPassword.requestFocus();
                    }
                } else {
                loginUsername.setError("User doesnot exist");
                loginUsername.requestFocus();
                }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
                });
                }
                }
                
