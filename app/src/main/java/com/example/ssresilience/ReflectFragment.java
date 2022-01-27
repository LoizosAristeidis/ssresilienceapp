package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReflectFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReflectFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String goal;
    private int reflectpoints = 0;

    public ReflectFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReflectFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReflectFragment newInstance(String param1, String param2) {
        ReflectFragment fragment = new ReflectFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_reflect, container, false);

        // Retrieve the selected Goal from the DataSite Class
        goal = ((DataSite)getActivity().getApplication()).getGoal();

        // Initialize the Fragment's TextViews
        TextView fg_reflect_question1 = (TextView)rootView.findViewById(R.id.fg_reflect_question1);
        TextView fg_reflect_question2 = (TextView)rootView.findViewById(R.id.fg_reflect_question2);
        TextView fg_reflect_question3 = (TextView)rootView.findViewById(R.id.fg_reflect_question3);
        TextView fg_reflect_question4 = (TextView)rootView.findViewById(R.id.fg_reflect_question4);

        ToggleButton fg_reflect_checkbox1 = (ToggleButton)rootView.findViewById(R.id.fg_reflect_checkbox1);
        fg_reflect_checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reflectpoints += 15;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                }
            }
        });

        ToggleButton fg_reflect_checkbox2 = (ToggleButton)rootView.findViewById(R.id.fg_reflect_checkbox2);
        fg_reflect_checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reflectpoints += 15;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                }
            }
        });

        ToggleButton fg_reflect_checkbox3 = (ToggleButton)rootView.findViewById(R.id.fg_reflect_checkbox3);
        fg_reflect_checkbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reflectpoints += 15;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                }
            }
        });

        ToggleButton fg_reflect_checkbox4 = (ToggleButton)rootView.findViewById(R.id.fg_reflect_checkbox4);
        fg_reflect_checkbox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reflectpoints += 15;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                }
            }
        });

        Button fg_reflect_submit = (Button)rootView.findViewById(R.id.fg_reflect_submit);
        fg_reflect_submit.setOnClickListener(this::onClick);

        // Fill the Fragment's TextViews according to the selected Goal
        if (goal != null) {
            if (goal.equals("socialize")) {
                fg_reflect_question1.setText("Talk to 3 or more people other than your family?");
                fg_reflect_question2.setText("Engage in phone or video calls from any device?");
                fg_reflect_question3.setText("Exit your house for more than 2 hours?");
                fg_reflect_question4.setText("Meet any new people online or via your phone?");
            }
            if (goal.equals("study")) {
                fg_reflect_question1.setText("Study for 1 or more hours today?");
                fg_reflect_question2.setText("Work for at least 1 hour on your projects?");
                fg_reflect_question3.setText("Talk to friends or relatives about your projects?");
                fg_reflect_question4.setText("Dedicate study time for the course of your interest?");
            }
            if (goal.equals("exercise")) {
                fg_reflect_question1.setText("Dedicate more than 1 hour on physical exercise?");
                fg_reflect_question2.setText("Participate in physical exercises with friends or others?");
                fg_reflect_question3.setText("Achieve your physical exercise-related goal?");
                fg_reflect_question4.setText("Track your fitness-related progress in any way?");
            }
        }
        return rootView;
    }

    @SuppressLint({"NonConstantResourceId", "UseCompatLoadingForDrawables"})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fg_reflect_submit:
                ((DataSite) getActivity().getApplication()).getReflectPoints();
                ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                Fragment fr2 = new ProgressFragment();
                FragmentManager fm2 = getFragmentManager();
                FragmentTransaction fragmentTransaction2 = fm2.beginTransaction();
                fragmentTransaction2.replace(R.id.fg_reflect_container, fr2);
                fragmentTransaction2.commit();
                break;
            default:
                break;
        }
    }
}