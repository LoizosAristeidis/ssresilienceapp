package com.example.ssresilience;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        // Retrieve the selected Goal from the Goals Fragment
        getParentFragmentManager().setFragmentResultListener("selected_goal3", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle3) {
                int goal3 = bundle3.getInt("goal3");

                // Set the Reflect Fragment Text according to the selected Goal
                TextView fg_reflect_question1 = (TextView)rootView.findViewById(R.id.fg_reflect_question1);
                TextView fg_reflect_question2 = (TextView)rootView.findViewById(R.id.fg_reflect_question2);
                TextView fg_reflect_question3 = (TextView)rootView.findViewById(R.id.fg_reflect_question3);
                TextView fg_reflect_question4 = (TextView)rootView.findViewById(R.id.fg_reflect_question4);
                if (goal3 == 1) {
                    fg_reflect_question1.setText("Talk to 3 or more people other than your family?");
                    fg_reflect_question2.setText("Engage in phone or video calls from any device?");
                    fg_reflect_question3.setText("Exit your house for more than 2 hours?");
                    fg_reflect_question4.setText("Meet any new people online or via your phone?");
                }
                if (goal3 == 2) {
                    fg_reflect_question1.setText("Study for 1 or more hours today?");
                    fg_reflect_question2.setText("Work for at least 1 hour on your projects?");
                    fg_reflect_question3.setText("Talk to friends or relatives about your projects?");
                    fg_reflect_question4.setText("Dedicate study time for the course of your interest?");
                }
                if (goal3 == 3) {
                    fg_reflect_question1.setText("Dedicate more than 1 hour on physical exercise?");
                    fg_reflect_question2.setText("Participate in physical exercises with friends or others?");
                    fg_reflect_question3.setText("Achieve your physical exercise-related goal?");
                    fg_reflect_question4.setText("Track your fitness-related progress in any way?");
                }
            }
        });


        return rootView;
    }
}