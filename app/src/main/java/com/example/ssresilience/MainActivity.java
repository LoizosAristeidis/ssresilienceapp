package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button register, login_btn;
    private EditText login_email, login_password;
    private ProgressBar progressBar;
    private String test;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (Button) findViewById(R.id.register_screen);
        register.setOnClickListener(this);

        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);

        login_email = (EditText) findViewById(R.id.login_email);
        login_password = (EditText) findViewById(R.id.login_password);

        progressBar = (ProgressBar) findViewById(R.id.login_progressBar);

        mAuth = FirebaseAuth.getInstance();

        // Hide the Action Bar for this Activity
        getSupportActionBar().hide();

        try {

            Intent intent = getIntent();
            String goal2 = intent.getStringExtra("goal2");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.register_screen:
                startActivity(new Intent(this, RegisterUserActivity.class));
                break;
            case R.id.login_btn:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = login_email.getText().toString().trim();
        String password = login_password.getText().toString().trim();

        if(email.isEmpty()) {
            login_email.setError("Email cannot be blank");
            login_email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            login_email.setError("Please enter a valid email");
            login_email.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            login_password.setError("Password is required");
            login_password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                startActivity(new Intent(MainActivity.this, HomePageActivity.class));
            } else {
                Toast.makeText(MainActivity.this, "Failed to Log In! Please check your credentials.", Toast.LENGTH_LONG).show();
            }
        });
    }
}