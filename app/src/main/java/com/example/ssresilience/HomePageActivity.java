package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import static com.example.ssresilience.R.color.buttoncolor_text;

// Purple Color: #3700b3

@SuppressWarnings("deprecation")
public class HomePageActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    private Button info_goals, info_measure_monitor, info_progress_rewards, info_share, info_reflect, button_back, button_menu;
    private TextView info_placeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Initialize the Navigation Menu
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
//        openFragment(GoalsFragment.newInstance("", ""));

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

        // Set all the labels of the navigation menu as visible
        bottomNavigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);


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

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @SuppressLint("NonConstantResourceId")
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_goals:
                            openFragment(GoalsFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_measure:
                            openFragment(MeasureFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_progress:
                            openFragment(ProgressFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_social:
                            openFragment(SocialFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_reflect:
                            openFragment(ReflectFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };
}