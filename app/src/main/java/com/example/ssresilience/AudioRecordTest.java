package com.example.ssresilience;

import android.content.ActivityNotFoundException;
import android.content.pm.ActivityInfo;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.print.PrintAttributes;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.io.IOException;

import static android.view.Gravity.CENTER;
import static android.view.Gravity.CENTER_HORIZONTAL;

public class AudioRecordTest extends AppCompatActivity {

    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String fileName = null;

    private RecordButton recordButton = null;
    private MediaRecorder recorder = null;

    private PlayButton playButton = null;
    private Button button_back;
    private MediaPlayer player = null;

    private TextView noisetitle;

    private ClipboardManager myClipboard;
    private ClipData myClip;

    private Button proceedButton;
    private int checkifmeasured = 0;
    private int audiolevelprocessed_progress;

    private FirebaseAuth mAuth;
    private DatabaseReference userRef;

    private int audiolevel;
    private int audiolevelprocessed;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(fileName);
            player.prepare();
            player.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        player.release();
        player = null;
    }

    void startRecording() {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        recorder.start();
        audiolevel = recorder.getMaxAmplitude();
    }

    private void stopRecording() {
        audiolevelprocessed = recorder.getMaxAmplitude();
        ((DataSite)getApplication()).setNoiseLevel(audiolevelprocessed);
        if (audiolevelprocessed < 10000) {
            audiolevelprocessed_progress = 15;
        }
        if ((audiolevelprocessed >= 10000) && (audiolevelprocessed < 20000)) {
            audiolevelprocessed_progress = 25;
        }
        if (audiolevelprocessed >= 20000) {
            audiolevelprocessed_progress = 40;
        }
        userRef.child("progress").setValue(ServerValue.increment(Long.valueOf(audiolevelprocessed_progress)));
        recorder.stop();
        recorder.release();
        recorder = null;
        checkifmeasured = 1;
        ((DataSite)getApplication()).setCheckIfMeasured(checkifmeasured);
    }

    class RecordButton extends androidx.appcompat.widget.AppCompatButton {
        boolean mStartRecording = true;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                if (checkifmeasured == 1) {
                    Toast.makeText(getApplicationContext(), "You have already finished the measurement! Please Proceed.",Toast.LENGTH_SHORT).show();
                } else {
                    onRecord(mStartRecording);
                    if (mStartRecording) {
                        setText("Stop recording");
                    } else {
                        setText("Start recording");
                    }
                    mStartRecording = !mStartRecording;
                }
            }
        };

        public RecordButton(Context ctx) {
            super(ctx);
            setText("Start recording");
            setOnClickListener(clicker);
        }
    }

    class PlayButton extends androidx.appcompat.widget.AppCompatButton {
        boolean mStartPlaying = true;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                onPlay(mStartPlaying);
                if (mStartPlaying) {
                    setText("Stop playing");
                } else {
                    setText("Start playing");
                }
                mStartPlaying = !mStartPlaying;
            }
        };

        public PlayButton(Context ctx) {
            super(ctx);
            setText("Start playing");
            setOnClickListener(clicker);
        }
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//Set Portrait

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

        // Record to the external cache directory for visibility
        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/audiorecordtest.3gp";

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child("Users").child(userId);

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

//        LinearLayout ll = new LinearLayout(this);
        LinearLayout.LayoutParams llparams;
        LinearLayout.LayoutParams llparams2;
        LinearLayout.LayoutParams llparams3;
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setBackgroundColor(Color.parseColor("#3700b3"));
        ll.setId(R.id.constraintlayout1);
        noisetitle = new TextView(this);
        noisetitle.setTextColor(Color.WHITE);
        noisetitle.setPadding(50, 0, 50, 0);
        noisetitle.setTextSize(32);
        noisetitle.setTypeface(null, Typeface.BOLD);
        noisetitle.setText("Record the noise level in your surrounding environment");
        noisetitle.setPadding(50, 0, 50, 0);
        noisetitle.setGravity(CENTER);
        ll.addView(noisetitle,
                llparams3 = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        0));
        llparams3.setMargins(50, 200, 50, 0);
        llparams3.gravity = CENTER_HORIZONTAL;
        noisetitle.setLayoutParams(llparams3);
        recordButton = new RecordButton(this);
        recordButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border_rect));
        recordButton.setTextColor(Color.WHITE);
        recordButton.setPadding(50, 0, 50, 0);
        recordButton.setText("Start Recording");
        recordButton.setId(R.id.recordButton);
        recordButton.setGravity(CENTER);
        ll.addView(recordButton,
                llparams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        0));
        llparams.setMargins(100, 500, 100, 0);
        llparams.gravity = CENTER_HORIZONTAL;
        recordButton.setLayoutParams(llparams);
        proceedButton = new Button(this);
        proceedButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
        proceedButton.setTextColor(Color.WHITE);
        proceedButton.setTextSize(26);
        proceedButton.setPadding(100, 50, 100, 50);
        proceedButton.setId(R.id.proceedButton);
        proceedButton.setText("Proceed");
        proceedButton.setGravity(Gravity.CENTER);
        proceedButton.setOnClickListener(v -> {
            if (checkifmeasured == 1 ) {
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Please finish the measurement before proceeding!",Toast.LENGTH_SHORT).show();
            }
        });
        ll.addView(proceedButton,
                llparams2 = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        0));
        llparams2.setMargins(100, 800, 100, 0);
        llparams2.gravity = CENTER_HORIZONTAL;
        proceedButton.setLayoutParams(llparams2);
        playButton = new PlayButton(this);
        playButton.setVisibility(View.INVISIBLE);
        ll.addView(playButton,
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        0));
        setContentView(ll);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (recorder != null) {
            recorder.release();
            recorder = null;
        }

        if (player != null) {
            player.release();
            player = null;
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onOptionsItemSelected(@androidx.annotation.NonNull MenuItem item) {
        DrawerLayout drawerLayout = findViewById(R.id.my_drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showTimePickerDialog(MenuItem item) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void logout(MenuItem item) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(AudioRecordTest.this, MainActivity.class));
    }

    public void contactus(MenuItem item) {
        String mailto = "mailto:ssresilienceapp@gmail.com" +
                "?cc=" +
                "&subject=" + Uri.encode("SSResilience App Support") +
                "&body=" + Uri.encode("");
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));

        try {
            startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Error to open email app", Toast.LENGTH_SHORT).show();
        }
    }
}