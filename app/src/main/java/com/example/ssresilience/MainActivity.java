package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button register, login_btn;
    private EditText login_email, login_password;
    private ProgressBar progressBar;

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
                //redirect to userprofile or wherever we want to redirect.. (main page / goal page)
                startActivity(new Intent(this, ProfileActivity.class));
            } else {
                Toast.makeText(MainActivity.this, "FAILED TO LOGIN! PLEASE CHECK YOUR CREDENTIALS", Toast.LENGTH_LONG).show();
            }
        });
    }
}