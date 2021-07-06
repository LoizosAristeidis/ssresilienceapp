package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText registerEmail, registerAge, registerFullName, registerPassword;
    private ProgressBar progressBar;
    private Button registerBtn, backToLogin;

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
        String age = registerAge.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();
        String fullName = registerFullName.getText().toString().trim();

        if(email.isEmpty()) {
            registerEmail.setError("E-mail IS REQUIRED");
            registerEmail.requestFocus();
            return;
        }

        if(fullName.isEmpty()) {
            registerFullName.setError("Full Name IS REQUIRED");
            registerFullName.requestFocus();
            return;
        }

        if(age.isEmpty()) {
            registerAge.setError("Age IS REQUIRED");
            registerAge.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            registerPassword.setError("Password IS REQUIRED");
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
        User user = new User(fullName, age, email);

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
                                startActivity(new Intent(this, MainActivity.class));
                            } else {
                                Toast.makeText(RegisterUserActivity.this, "Failed to register! Try again", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.VISIBLE);
                            }
                        });
                }
            });
    }
}