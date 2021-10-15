package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

 import com.example.ssresilience.R;
 import com.example.ssresilience.SelectedExerciseGoal;
 import com.example.ssresilience.SelectedSocializeGoal;
 import com.example.ssresilience.SelectedStudyGoal;

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

    private Button fg_goals_socialize, fg_goals_study, fg_goals_exercise, fg_goals_setgoal;

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
        // Initialize the 3 buttons
        View rootView = inflater.inflate((R.layout.fragment_goals), container, false);

        fg_goals_socialize = (Button)rootView.findViewById(R.id.fg_goals_socialize);
        fg_goals_socialize.setOnClickListener(this::onClick);
        fg_goals_study = (Button)rootView.findViewById(R.id.fg_goals_study);
        fg_goals_study.setOnClickListener(this::onClick);
        fg_goals_exercise = (Button)rootView.findViewById(R.id.fg_goals_exercise);
        fg_goals_exercise.setOnClickListener(this::onClick);
        fg_goals_setgoal = (Button)rootView.findViewById(R.id.fg_goals_setgoal);
        fg_goals_setgoal.setOnClickListener(this::onClick);

        return rootView;
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor", "UseCompatLoadingForColorStateLists", "NonConstantResourceId", "SetTextI18n"})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fg_goals_socialize:
                fg_goals_socialize.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect));
                fg_goals_socialize.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text));
                fg_goals_study.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect_reset));
                fg_goals_study.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                fg_goals_exercise.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect_reset));
                fg_goals_exercise.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                check = 1;
                break;
            case R.id.fg_goals_study:
                fg_goals_socialize.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect_reset));
                fg_goals_socialize.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                fg_goals_study.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect));
                fg_goals_study.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text));
                fg_goals_exercise.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect_reset));
                fg_goals_exercise.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                check = 2;
                break;
            case R.id.fg_goals_exercise:
                fg_goals_socialize.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect_reset));
                fg_goals_socialize.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                fg_goals_study.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect_reset));
                fg_goals_study.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text_reset));
                fg_goals_exercise.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttoncolor_rect));
                fg_goals_exercise.setTextColor(getResources().getColorStateList(R.color.buttoncolor_text));
                check = 3;
                break;
            case R.id.fg_goals_setgoal:
                if(check == 0) {
                    Toast.makeText(getActivity(), "Please select one of the 3 Goals!",
                            Toast.LENGTH_LONG).show();
                }
                if(check == 1) {
                    Intent intent = new Intent(getActivity(), SelectedSocializeGoal.class);
                    startActivity(intent);
                }
                if(check == 2) {
                    Intent intent = new Intent(getActivity(), SelectedStudyGoal.class);
                    startActivity(intent);
                }
                if(check == 3) {
                    Intent intent = new Intent(getActivity(), SelectedExerciseGoal.class);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }
}