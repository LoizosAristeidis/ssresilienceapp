package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ssresilience.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText registerEmail, registerAge, registerFullName, registerPassword;
    private ProgressBar progressBar;
    private Button registerBtn, backToLogin;
    private int progress1 = 0;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        registerBtn = (Button) findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(this);

        backToLogin = (Button) findViewById(R.id.back_to_login);
        backToLogin.setOnClickListener(this);

        registerEmail = (EditText) findViewById(R.id.register_email);
        registerFullName = (EditText) findViewById(R.id.register_fullname);
        registerAge = (EditText) findViewById(R.id.register_age);
        registerPassword = (EditText) findViewById(R.id.register_password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Hide the Action Bar for this Activity
        getSupportActionBar().hide();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.back_to_login:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.register_btn:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = registerEmail.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();
        String fullName = registerFullName.getText().toString().trim();
        Long progress = Long.valueOf(progress1);

        if(email.isEmpty()) {
            registerEmail.setError("E-mail address is required");
            registerEmail.requestFocus();
            return;
        }

        if(fullName.isEmpty()) {
            registerFullName.setError("Full Name is required");
            registerFullName.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            registerPassword.setError("Password is required");
            registerPassword.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            registerEmail.setError("Please provide a valid email address");
            registerEmail.requestFocus();
            return;
        }

        if(password.length() < 6) {
            registerPassword.setError("Password must be BIGGER than 6 characters");
            registerPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        User user = new User(fullName, email, progress);

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    // Write a message to the database
                    FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(user).addOnCompleteListener(task1 -> {
                            if(task1.isSuccessful()) {
                                Toast.makeText(RegisterUserActivity.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                //redirect to login layout (main activity) after signup
//                                FirebaseUser authUser = FirebaseAuth.getInstance().getCurrentUser();
//                                authUser.sendEmailVerification();
                                startActivity(new Intent(this, MainActivity.class));
                            } else {
                                Toast.makeText(RegisterUserActivity.this, "Failed to register! Try again", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                }
            });
    }
}