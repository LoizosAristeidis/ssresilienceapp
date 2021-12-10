package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeasureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeasureFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String goal;
    private Button fg_measure_button_go;

    public MeasureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeasureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeasureFragment newInstance(String param1, String param2) {
        MeasureFragment fragment = new MeasureFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_measure, container, false);

        // Retrieve the selected Goal from the DataSite Class
        goal = ((DataSite)getActivity().getApplication()).getGoal();

        // Initialize the Fragment's TextViews
        TextView fg_measure_title = (TextView)rootView.findViewById(R.id.fg_measure_title);
        TextView fg_measure_text = (TextView)rootView.findViewById(R.id.fg_measure_text);
        Button fg_measure_button_go = (Button)rootView.findViewById(R.id.fg_measure_button_go);
        fg_measure_button_go.setOnClickListener(this::onClick);


        // Fill the Fragment's TextViews according to the selected Goal
        if (goal != null) {
            if (goal.equals("socialize")) {
                fg_measure_title.setText("Socialize More");
                fg_measure_text.setText("Measure the level of noise of your surrounding environment, in order to let the app know you're socializing with people.");
            }
            if (goal.equals("study")) {
                fg_measure_title.setText("Enhance Study Motives");
                fg_measure_text.setText("Allow the app to calculate and determine your stress level, by taking the Stress Test. Remember, a higher stress level leads to lower study motives.");
            }
            if (goal.equals("exercise")) {
                fg_measure_title.setText("Physical Exercise");
                fg_measure_text.setText("Measure the level of your current physical activity state by utilizing your device's accelerometer sensor.");
            }
        }

        return rootView;
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor", "UseCompatLoadingForColorStateLists", "NonConstantResourceId", "SetTextI18n"})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fg_measure_button_go:
                Toast.makeText(getActivity(), "Coming Soon!",
                        Toast.LENGTH_LONG).show();
                break;
        }
    }
}