package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.ssresilience.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    private int check;
    private String goal, dbgoal, dbreflect;
    private FirebaseUser user;
    private int reflectpoints = 0;
    private FirebaseAuth mAuth;
    private String updateD;
    private DatabaseReference userRef, dbReference;

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

        check = ((DataSite)getActivity().getApplication()).getCheck();
        if (check == 2) {
            Toast.makeText(getActivity(), "You have already used the Reflect Tab.\nPlease change your selected Goal to start over!",
                    Toast.LENGTH_LONG).show();
        }

        // Retrieve the selected Goal from the DataSite Class
        goal = ((DataSite)getActivity().getApplication()).getGoal();

        // Initialize the Fragment's TextViews
        TextView fg_reflect_question1 = (TextView)rootView.findViewById(R.id.fg_reflect_question1);
        TextView fg_reflect_question2 = (TextView)rootView.findViewById(R.id.fg_reflect_question2);
        TextView fg_reflect_question3 = (TextView)rootView.findViewById(R.id.fg_reflect_question3);
        TextView fg_reflect_question4 = (TextView)rootView.findViewById(R.id.fg_reflect_question4);

        ToggleButton fg_reflect_checkbox1 = (ToggleButton)rootView.findViewById(R.id.fg_reflect_checkbox1);
        ToggleButton fg_reflect_checkbox2 = (ToggleButton)rootView.findViewById(R.id.fg_reflect_checkbox2);
        ToggleButton fg_reflect_checkbox3 = (ToggleButton)rootView.findViewById(R.id.fg_reflect_checkbox3);
        ToggleButton fg_reflect_checkbox4 = (ToggleButton)rootView.findViewById(R.id.fg_reflect_checkbox4);


        fg_reflect_checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reflectpoints += 15;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                } else if (!isChecked) {
                    reflectpoints -= 15;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                }
                else {
                    reflectpoints += 0;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                }
            }
        });
        fg_reflect_checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked ) {
                    reflectpoints += 15;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                } else if (!isChecked) {
                    reflectpoints -= 15;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                }
                else {
                    reflectpoints += 0;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                }
            }
        });
        fg_reflect_checkbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reflectpoints += 15;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                } else if (!isChecked) {
                    reflectpoints -= 15;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                } else {
                    reflectpoints += 0;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                }
            }
        });
        fg_reflect_checkbox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    reflectpoints += 15;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                } else if (!isChecked) {
                    reflectpoints -= 15;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                } else {
                    reflectpoints += 0;
                    ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                }
            }
        });

        Button fg_reflect_submit = (Button)rootView.findViewById(R.id.fg_reflect_submit);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child("Users").child(userId);

        //get the logged in user from the auth
        user = FirebaseAuth.getInstance().getCurrentUser();
        //users are stored in /Users endpoint of our database so we have to create the database reference
        //to the database
        dbReference = FirebaseDatabase.getInstance().getReference("Users");
        //get the user id from the already created user instance of the firebase
        userId = user.getUid();

        //now we need to get the details from the realtime database by using the child method
        String finalUserId = userId;
        dbReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@androidx.annotation.NonNull @NotNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    dbgoal = userProfile.goal;
                    dbreflect = userProfile.reflect;
                    Calendar c = Calendar.getInstance();
                    System.out.println("Current time => "+ c.getTime());
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    updateD = df.format(c.getTime());
                    // Fill the Fragment's TextViews according to the selected Goal
                    if (dbgoal != null) {
                        if (dbgoal.equals("Socialize More")) {
                            fg_reflect_question1.setText("Talk to 3 or more people other than your family?");
                            fg_reflect_question2.setText("Engage in phone or video calls from any device?");
                            fg_reflect_question3.setText("Exit your house for more than 2 hours?");
                            fg_reflect_question4.setText("Meet any new people online or via your phone?");
                        }
                        if (dbgoal.equals("Enhance Study Motives")) {
                            fg_reflect_question1.setText("Study for 1 or more hours today?");
                            fg_reflect_question2.setText("Work for at least 1 hour on your projects?");
                            fg_reflect_question3.setText("Talk to friends or relatives about your projects?");
                            fg_reflect_question4.setText("Dedicate study time for the course of your interest?");
                        }
                        if (dbgoal.equals("Physical Exercise")) {
                            fg_reflect_question1.setText("Dedicate more than 1 hour on physical exercise?");
                            fg_reflect_question2.setText("Participate in physical exercises with friends or others?");
                            fg_reflect_question3.setText("Achieve your physical exercise-related goal?");
                            fg_reflect_question4.setText("Track your fitness-related progress in any way?");
                        }
                        fg_reflect_submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ((DataSite) getActivity().getApplication()).getReflectPoints();
                                ((DataSite) getActivity().getApplication()).setReflectPoints(reflectpoints);
                                if (dbreflect.equals("yes")) {
                                    Toast.makeText(getActivity(), "You have already used the Reflect Tab.\nPlease change your selected Goal to start over!",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    dbReference.child(finalUserId).child("reflect").setValue("yes");
                                    dbReference.child(finalUserId).child("updateD").setValue(updateD);
                                    userRef.child("progress").setValue(ServerValue.increment(Long.valueOf(reflectpoints)));
                                    Fragment fr2 = new ProgressFragment();
                                    FragmentManager fm2 = getFragmentManager();
                                    FragmentTransaction fragmentTransaction2 = fm2.beginTransaction();
                                    fragmentTransaction2.replace(R.id.fg_reflect_container, fr2);
                                    fragmentTransaction2.commit();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "Please select a Goal first!",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(getActivity(), "Cannot retrieve User due to an error.",
                        Toast.LENGTH_LONG).show();
            }

        });

        return rootView;
    }
}