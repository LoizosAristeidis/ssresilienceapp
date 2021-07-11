package com.example.ssresilience;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;

// Purple Color: #3700b3

public class HomePageActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    private Button info_goals, info_measure_monitor, info_progress_rewards, info_share, info_reflect;
    private TextView info_placeholder;
    private boolean tmp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
//        openFragment(GoalsFragment.newInstance("", ""));

        info_goals = findViewById(R.id.info_goals);
        info_measure_monitor = findViewById(R.id.info_measure_monitor);
        info_progress_rewards = findViewById(R.id.info_progress_rewards);
        info_share = findViewById(R.id.info_share);
        info_reflect = findViewById(R.id.info_reflect);
        info_placeholder = findViewById(R.id.info_placeholder);

        info_goals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (info_placeholder.getText().toString().equals("")) {
                    info_placeholder.setText("You can set a different goal each day");
                }

            }
        });

        info_measure_monitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (info_placeholder.getText().toString().equals("")) {
                    info_placeholder.setText("Use your device's sensors or the stress test to self-report your emotions");
                }
            }
        });

        info_progress_rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (info_placeholder.getText().toString().equals("")) {
                    info_placeholder.setText("These are based on your progress within the app");
                }
            }
        });

        info_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (info_placeholder.getText().toString().equals("")) {
                    info_placeholder.setText("Compete or Collaborate with other users");
                }
            }
        });

        info_reflect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (info_placeholder.getText().toString().equals("")) {
                    info_placeholder.setText("Be your own judge at the end of each day");
                }
            }
        });
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
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