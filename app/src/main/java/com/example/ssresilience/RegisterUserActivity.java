package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ssresilience.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterUserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText registerEmail, registerAge, registerFullName, registerPassword;
    private ProgressBar progressBar;
    private Button registerBtn, backToLogin;
    private String goal, measureme, reflect, createD, updateD;
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

        goal = "";
        measureme = "no";
        reflect = "no";

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
        String goal = "";
        Long progress = Long.valueOf(progress1);
        String measureme = "no";
        String reflect = "no";
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+ c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createD = df.format(c.getTime());
        Calendar c2 = Calendar.getInstance();
        System.out.println("Current time => "+ c2.getTime());
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String updateD = df2.format(c2.getTime());

        if(email.isEmpty()) {
            registerEmail.setError("Η διεύθυνση Email είναι αναγκαία.");
            registerEmail.requestFocus();
            return;
        }

        if(fullName.isEmpty()) {
            registerFullName.setError("Το όνομα είναι αναγκαίο.");
            registerFullName.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            registerPassword.setError("Ο κωδικός είναι αναγκαίος.");
            registerPassword.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            registerEmail.setError("Παρακαλώ εισάγετε το Email σας σωστά.");
            registerEmail.requestFocus();
            return;
        }

        if(password.length() < 6) {
            registerPassword.setError("Ο κωδικός πρέπει να είναι 6 ή περισσότερους χαρακτήρες.");
            registerPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        User user = new User(fullName, email, goal, progress, measureme, reflect, createD, updateD);

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    // Write a message to the database
                    FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(user).addOnCompleteListener(task1 -> {
                            if(task1.isSuccessful()) {
                                Toast.makeText(RegisterUserActivity.this, "Ο χρήστης δημιουργήθηκε επιτυχώς.", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                startActivity(new Intent(this, MainActivity.class));
                            } else {
                                Toast.makeText(RegisterUserActivity.this, "Κάτι πήγε στραβά, παρακαλώ δοκιμάστε ξανά.", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                }
            });
    }
}