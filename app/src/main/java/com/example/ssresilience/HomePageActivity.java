package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.ssresilience.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static android.Manifest.*;
import static com.example.ssresilience.R.color.buttoncolor_text;

// Purple Color: #3700b3

@SuppressWarnings("deprecation")
public class HomePageActivity extends AppCompatActivity {
    private Button info_goals, info_measure_monitor, info_progress_rewards, info_share, info_reflect, button_back, button_proceed;
    private TextView info_placeholder;
    private FirebaseUser user;
    private DatabaseReference dbReference;
    private String userId;
    private TextView fullNameTextView;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    private int amplitudeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Initialize the Homepage buttons
        info_goals = findViewById(R.id.info_goals);
        info_goals.setOnClickListener(this::onClick);
        info_measure_monitor = findViewById(R.id.info_measure_monitor);
        info_measure_monitor.setOnClickListener(this::onClick);
        info_progress_rewards = findViewById(R.id.info_progress_rewards);
        info_progress_rewards.setOnClickListener(this::onClick);
        info_share = findViewById(R.id.info_share);
        info_share.setOnClickListener(this::onClick);
        info_reflect = findViewById(R.id.info_reflect);
        info_reflect.setOnClickListener(this::onClick);
        info_placeholder = findViewById(R.id.info_placeholder);


        //get the logged in user from the auth
        user = FirebaseAuth.getInstance().getCurrentUser();
        //users are stored in /Users endpoint of our database so we have to create the database reference
        //to the database
        dbReference = FirebaseDatabase.getInstance().getReference("Users");
        //get the user id from the already created user instance of the firebase
        userId = user.getUid();
        fullNameTextView = (TextView) findViewById(R.id.welcome_user);

        //now we need to get the details from the realtime database by using the child method
        dbReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null) {
                    String fullNameValue = userProfile.fullName;
                    fullNameTextView.setText("Welcome " + fullNameValue);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(HomePageActivity.this, "Could not retrieve the username.", Toast.LENGTH_LONG).show();
            }
        });

        // Action Bar Styling
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#FFFFFF"));
        actionBar.setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // Back button functionality
        button_back = findViewById(R.id.back_button);
        button_back.setOnClickListener(v -> {
            this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
            this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
        });

        // Proceed button functionality
        button_proceed = (Button) findViewById(R.id.button_proceed);
        button_proceed.setOnClickListener(v -> {
            startActivity(new Intent(HomePageActivity.this, InitialScreen.class));
//            SoundMeter sm = new SoundMeter();
//            sm.start();
//            double amp = sm.getAmplitude();
//            System.out.println(amp);
        });

        // Drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        DrawerLayout drawerLayout = findViewById(R.id.my_drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // Pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // Make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Put the Navigation drawer icon to the right side of the screen
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawerLayout = findViewById(R.id.my_drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor", "UseCompatLoadingForColorStateLists", "NonConstantResourceId", "SetTextI18n"})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_goals:
                    info_placeholder.setText("You can set a different goal each day");
                    info_goals.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor));
                    info_goals.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text));
                    info_measure_monitor.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_measure_monitor.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_progress_rewards.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_progress_rewards.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_share.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_share.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_reflect.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_reflect.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                break;
            case R.id.info_measure_monitor:
                    info_placeholder.setText("Use your device's sensors or the stress test to self-report your emotions");
                    info_goals.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_goals.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_measure_monitor.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor));
                    info_measure_monitor.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text));
                    info_progress_rewards.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_progress_rewards.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_share.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_share.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_reflect.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_reflect.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                break;
            case R.id.info_progress_rewards:
                    info_placeholder.setText("These are based on your progress within the app");
                    info_goals.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_goals.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_measure_monitor.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_measure_monitor.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_progress_rewards.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor));
                    info_progress_rewards.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text));
                    info_share.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_share.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_reflect.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_reflect.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                break;
            case R.id.info_share:
                    info_placeholder.setText("Compete or Collaborate with other users");
                    info_goals.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_goals.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_measure_monitor.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_measure_monitor.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_progress_rewards.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_progress_rewards.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_share.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor));
                    info_share.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text));
                    info_reflect.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_reflect.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                break;
            case R.id.info_reflect:
                    info_placeholder.setText("Be your own judge at the end of each day");
                    info_goals.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_goals.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_measure_monitor.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_measure_monitor.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_progress_rewards.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_progress_rewards.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_share.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_reset));
                    info_share.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                    info_reflect.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor));
                    info_reflect.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text));
                break;
            default:
                break;
        }
    }


    public void showTimePickerDialog(MenuItem item) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void logout(MenuItem item) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(HomePageActivity.this, MainActivity.class));
    }

    public void contactus(MenuItem item) {
        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        String text = "ssresilienceapp@gmail.com";

        myClip = ClipData.newPlainText("text", text);
        myClipboard.setPrimaryClip(myClip);

        Toast.makeText(getApplicationContext(), "Email Address Copied!",Toast.LENGTH_SHORT).show();
    }
}