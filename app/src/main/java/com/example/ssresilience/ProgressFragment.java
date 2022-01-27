package com.example.ssresilience;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProgressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgressFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String goal;
    private int gadpoints, gadscore, reflectpoints, progress;

    public ProgressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProgressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProgressFragment newInstance(String param1, String param2) {
        ProgressFragment fragment = new ProgressFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_progress, container, false);

        // Retrieve the selected Goal from the DataSite Class
        goal = ((DataSite)getActivity().getApplication()).getGoal();
        gadpoints = ((DataSite) getActivity().getApplication()).getGadPoints();
        reflectpoints = ((DataSite) getActivity().getApplication()).getReflectPoints();


        // Initialize the Fragment's TextViews
        TextView fg_progress_header2 = (TextView) rootView.findViewById(R.id.fg_progress_header2);
        ProgressBar fg_progress_bar = (ProgressBar)rootView.findViewById(R.id.fg_progress_bar);
        fg_progress_bar.setProgressTintList(ColorStateList.valueOf(Color.WHITE));
        TextView fg_progress_award = (TextView) rootView.findViewById(R.id.fg_progress_award);
        TextView fg_progress_badge = (TextView) rootView.findViewById(R.id.fg_progress_badge);

        if (gadpoints < 7) {
            gadscore = 30;
            Toast.makeText(getActivity(), "New Award and Badge Unlocked!",
                    Toast.LENGTH_LONG).show();
            fg_progress_award.setText("• Low Level of Stress.");
            fg_progress_badge.setText("• Unstressed!");
        }
        if ((gadpoints >= 7) && (gadpoints < 14)) {
            gadscore = 20;
            Toast.makeText(getActivity(), "New Award Unlocked!",
                    Toast.LENGTH_LONG).show();
            fg_progress_award.setText("• Medium or Mild Level of Stress.");
        }
        if (gadpoints >= 14) {
            gadscore = 10;
        }

        fg_progress_bar.setProgress(gadscore + reflectpoints);
        progress = fg_progress_bar.getProgress();
        TextView fg_progress_points = (TextView)rootView.findViewById(R.id.fg_progress_points);
        fg_progress_points.setText(String.valueOf(gadscore + reflectpoints));

//        if ((progress > 0) && (progress <= 10)) {
            // Fill up the rest
//        }

        // Fill the Fragment's TextViews according to the selected Goal
        if (goal != null) {
            if (goal.equals("socialize")) {
                fg_progress_header2.setText("Socialize More");
            }
            if (goal.equals("study")) {
                fg_progress_header2.setText("Enhance Study Motives");
            }
            if (goal.equals("exercise")) {
                fg_progress_header2.setText("Physical Exercise");
            }
        }

        return rootView;
    }
}