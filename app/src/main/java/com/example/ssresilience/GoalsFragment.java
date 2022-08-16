package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoalsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoalsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button fg_goals_socialize;
    private Button fg_goals_study;
    private Button fg_goals_exercise;
    private Button fg_goals_setgoal;
    private TextView fg_goals_placeholder;
    private FirebaseUser user;
    private int reflectpoints = 0;
    private String updateD;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef, dbReference;

    private int gadpoints;
    private int check = 0;

    public GoalsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GoalsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoalsFragment newInstance(String param1, String param2) {
        GoalsFragment fragment = new GoalsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate((R.layout.fragment_goals), container, false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        dbReference = rootRef.child("Users").child(userId);
        fg_goals_socialize = (Button)rootView.findViewById(R.id.fg_goals_socialize);
        fg_goals_socialize.setOnClickListener(this::onClick);
        fg_goals_study = (Button)rootView.findViewById(R.id.fg_goals_study);
        fg_goals_study.setOnClickListener(this::onClick);
        fg_goals_exercise = (Button)rootView.findViewById(R.id.fg_goals_exercise);
        fg_goals_exercise.setOnClickListener(this::onClick);
        fg_goals_setgoal = (Button)rootView.findViewById(R.id.fg_goals_setgoal);
        fg_goals_setgoal.setOnClickListener(this::onClick);

        fg_goals_placeholder = (TextView)rootView.findViewById(R.id.fg_goals_placeholder);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+ c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        updateD = df.format(c.getTime());

        return rootView;
    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();
        }
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor", "UseCompatLoadingForColorStateLists", "NonConstantResourceId", "SetTextI18n"})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fg_goals_socialize:
                fg_goals_placeholder.setText("• Talk to 3 people other than the ones you live with (family/roommates).\n\n" +
                        "• Meet with a friend or family for lunch or dinner.\n\n" +
                        "• Go for a coffee or drink with company.\n\n" +
                        "• Interact in any shape or form with a person you've never met before.");
                fg_goals_socialize.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect));
                fg_goals_socialize.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text));
                fg_goals_study.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect_reset));
                fg_goals_study.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                fg_goals_exercise.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect_reset));
                fg_goals_exercise.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                check = 1;
                break;
            case R.id.fg_goals_study:
                fg_goals_placeholder.setText("• Study for 30 minutes or more hours today.\n\n" +
                        "• Work for at least 30 minutes on your projects.\n\n" +
                        "• Talk to friends or relatives about your projects.\n\n" +
                        "• Dedicate study time for the course of your liking.");
                fg_goals_socialize.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect_reset));
                fg_goals_socialize.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                fg_goals_study.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect));
                fg_goals_study.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text));
                fg_goals_exercise.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect_reset));
                fg_goals_exercise.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                check = 2;
                break;
            case R.id.fg_goals_exercise:
                fg_goals_placeholder.setText("• Dedicate more than 30 minutes on physical exercise.\n\n" +
                        "• Participate in physical exercises with friends or others.\n\n" +
                        "• Achieve your physical exercise-related goal.\n\n" +
                        "• Track your fitness-related progress in any way.");
                fg_goals_socialize.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect_reset));
                fg_goals_socialize.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                fg_goals_study.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect_reset));
                fg_goals_study.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                fg_goals_exercise.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect));
                fg_goals_exercise.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text));
                check = 3;
                break;
            default:
                break;
        }
        // Check which Goal is selected, then create a Bundle to share the selected goal variable across all fragments
        // 1 = Socialize More, 2 = Enhance Study Motives, 3 = Physical Exercise
        switch (v.getId()) {
            case R.id.fg_goals_setgoal:
                if(check == 0) {
                    Toast.makeText(getActivity(), "Please select one of the 3 Goals!",
                            Toast.LENGTH_LONG).show();
                }
                if(check == 1) {
                    Toast.makeText(getActivity(), "Current Goal: Socialize More",
                            Toast.LENGTH_LONG).show();
                    ((DataSite) getActivity().getApplication()).setGoal("socialize");
                    ((DataSite) getActivity().getApplication()).setCheck(0);
                    ((DataSite) getActivity().getApplication()).setGadPoints(0);
                    ((DataSite) getActivity().getApplication()).setReflectPoints(0);
                    ((DataSite) getActivity().getApplication()).setExercisePoints(0);
                    dbReference.child("progress").setValue(0);
                    dbReference.child("goal").setValue("Socialize More");
                    dbReference.child("measureme").setValue("no");
                    dbReference.child("reflect").setValue("no");
                    dbReference.child("updateD").setValue(updateD);
                    Fragment fr = new MeasureFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fg_goals_container, fr);
                    fragmentTransaction.commit();
                }
                if(check == 2) {
                    Toast.makeText(getActivity(), "Current Goal: Enhance Study Motives",
                            Toast.LENGTH_LONG).show();
                    ((DataSite) getActivity().getApplication()).setGoal("study");
                    ((DataSite) getActivity().getApplication()).setCheck(0);
                    ((DataSite) getActivity().getApplication()).setGadPoints(0);
                    ((DataSite) getActivity().getApplication()).setReflectPoints(0);
                    ((DataSite) getActivity().getApplication()).setExercisePoints(0);
                    dbReference.child("progress").setValue(0);
                    dbReference.child("goal").setValue("Enhance Study Motives");
                    dbReference.child("measureme").setValue("no");
                    dbReference.child("reflect").setValue("no");
                    dbReference.child("updateD").setValue(updateD);
                    Fragment fr = new MeasureFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fg_goals_container, fr);
                    fragmentTransaction.commit();
                }
                if(check == 3) {
                    Toast.makeText(getActivity(), "Current Goal: Physical Exercise",
                            Toast.LENGTH_LONG).show();
                    ((DataSite) getActivity().getApplication()).setGoal("exercise");
                    ((DataSite) getActivity().getApplication()).setCheck(0);
                    ((DataSite) getActivity().getApplication()).setGadPoints(0);
                    ((DataSite) getActivity().getApplication()).setReflectPoints(0);
                    ((DataSite) getActivity().getApplication()).setExercisePoints(0);
                    dbReference.child("progress").setValue(0);
                    dbReference.child("goal").setValue("Physical Exercise");
                    dbReference.child("measureme").setValue("no");
                    dbReference.child("reflect").setValue("no");
                    dbReference.child("updateD").setValue(updateD);
                    Fragment fr = new MeasureFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.fg_goals_container, fr);
                    fragmentTransaction.commit();
                }
                break;
            default:
                break;
        }
    }
}