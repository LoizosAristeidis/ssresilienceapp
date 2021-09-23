package com.example.ssresilience;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SelectedSocializeGoal extends AppCompatActivity {

    private TextView socialize_title, socialize_header, socialize_bp1, socialize_bp2, socialize_bp3, socialize_bp4;
    private Button button_back, socialize_button;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_socialize_goal);

        socialize_title = findViewById(R.id.socialize_title);
        socialize_header = findViewById(R.id.socialize_header);
        socialize_bp1 = findViewById(R.id.socialize_bp_1);
        socialize_bp2 = findViewById(R.id.socialize_bp_2);
        socialize_bp3 = findViewById(R.id.socialize_bp_3);
        socialize_bp4 = findViewById(R.id.socialize_bp_4);
        socialize_button = findViewById(R.id.socialize_button);

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

        // Put the Navigation drawer icon to the right side of the screen
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }
}