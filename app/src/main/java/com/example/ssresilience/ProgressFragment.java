package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssresilience.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

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
    private FirebaseUser user;
    private DatabaseReference dbReference;
    private String userId;
    private Long progressValue;
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

        if ((gadpoints != 0) && (reflectpoints == 0)) {
            ((DataSite) getActivity().getApplication()).setCheck(1);
        }
        if ((gadpoints == 0) && (reflectpoints != 0)) {
            ((DataSite) getActivity().getApplication()).setCheck(2);
        }
        if ((gadpoints != 0) && (reflectpoints != 0)){
            ((DataSite) getActivity().getApplication()).setCheck(3);
        }

        //get the logged in user from the auth
        user = FirebaseAuth.getInstance().getCurrentUser();
        //users are stored in /Users endpoint of our database so we have to create the database reference
        //to the database
        dbReference = FirebaseDatabase.getInstance().getReference("Users");
        //get the user id from the already created user instance of the firebase
        userId = user.getUid();

        //now we need to get the details from the realtime database by using the child method
        dbReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null) {
                    // Initialize the Fragment's TextViews
                    TextView fg_progress_header2 = (TextView) rootView.findViewById(R.id.fg_progress_header2);
                    ProgressBar fg_progress_bar = (ProgressBar)rootView.findViewById(R.id.fg_progress_bar);
                    fg_progress_bar.setProgressTintList(ColorStateList.valueOf(Color.WHITE));
                    TextView fg_progress_award = (TextView) rootView.findViewById(R.id.fg_progress_award);
                    TextView fg_progress_badge = (TextView) rootView.findViewById(R.id.fg_progress_badge);

                    progressValue = userProfile.progress;
                    progress = progressValue.intValue();
                    fg_progress_bar.setProgress(progress);
                    TextView fg_progress_points = (TextView)rootView.findViewById(R.id.fg_progress_points);
                    fg_progress_points.setText(String.valueOf(progress));

                    if (progress < 25) {
                        fg_progress_award.setText("• Low Level of Stress.");
                        fg_progress_badge.setText("• Unstressed!");
                    }
                    if ((progress >= 25) && (progress < 50)) {
                        fg_progress_award.setText("• Medium or Mild Level of Stress.");
                    }
                    if ((progress >= 50) && (progress < 75)) {
                    }
                    if (progress >= 75) {
                        fg_progress_award.setText("• Low Level of Stress.");
                        fg_progress_badge.setText("• Unstressed!");
                    }

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
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(getActivity(), "Cannot retrieve User score due to an error.",
                        Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }
}