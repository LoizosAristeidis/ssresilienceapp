package com.example.ssresilience;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectedStudyGoal extends AppCompatActivity {

    private TextView study_title, study_header, study_bp1, study_bp2, study_bp3, study_bp4;
    private Button button_back, study_button;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_study_goal);

        study_title = findViewById(R.id.study_title);
        study_header = findViewById(R.id.study_header);
        study_bp1 = findViewById(R.id.study_bp_1);
        study_bp2 = findViewById(R.id.study_bp_2);
        study_bp3 = findViewById(R.id.study_bp_3);
        study_bp4 = findViewById(R.id.study_bp_4);
        study_button = findViewById(R.id.study_button);
//        study_button.setOnClickListener(this::onClick);

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

//    public void onClick(View v) {
//        Intent intent = new Intent(this, MeasureStudy.class);
//        startActivity(intent);
//    }
}