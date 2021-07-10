package com.example.ssresilience;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssresilience.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ProfileActivity extends AppCompatActivity {

    private Button profile_logout;
    private FirebaseUser user;
    private DatabaseReference dbReference;
    private String userId;
    private TextView emailAddressTextView, fullNameTextView, ageTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //get the logged in user from the auth
        user = FirebaseAuth.getInstance().getCurrentUser();
        //users are stored in /Users endpoint of our database so we have to create the database reference
        //to the database
        dbReference = FirebaseDatabase.getInstance().getReference("Users");
        //get the user id from the already created user instance of the firebase
        userId = user.getUid();

        emailAddressTextView = (TextView) findViewById(R.id.profile_email_value);
        fullNameTextView = (TextView) findViewById(R.id.profile_fullname_value);
        ageTextView = (TextView) findViewById(R.id.profile_age_value);

        //now we need to get the details from the realtime database by using the child method
        dbReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null) {
                    String fullNameValue = userProfile.fullName;
                    String emailValue = userProfile.email;
                    String ageValue = userProfile.age;

                    emailAddressTextView.setText(emailValue);
                    fullNameTextView.setText(fullNameValue);
                    ageTextView.setText(ageValue);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "SOMETHING WRONG HAPPENED!!", Toast.LENGTH_LONG).show();
            }
        });

        //initialize the logout button
        profile_logout = (Button) findViewById(R.id.profile_logout);

        //logout the user on click using the firebase auth...
        profile_logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        });
    }
}