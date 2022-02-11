package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhysicalExercise#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhysicalExercise extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SensorManager sensorManager;
    private Sensor sensor;
    private TriggerEventListener triggerEventListener;
    private int exercisecheck;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;


    public PhysicalExercise() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhysicalExercise.
     */
    // TODO: Rename and change types and number of parameters
    public static PhysicalExercise newInstance(String param1, String param2) {
        PhysicalExercise fragment = new PhysicalExercise();
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
        View rootView = inflater.inflate(R.layout.fragment_physical_exercise, container, false);

        TextView fg_physical_exercise_title = (TextView)rootView.findViewById(R.id.fg_physical_exercise_title);
        TextView fg_physical_exercise_text = (TextView)rootView.findViewById(R.id.fg_physical_exercise_text);
        TextView fg_physical_exercise_result_title = (TextView)rootView.findViewById(R.id.fg_physical_exercise_result_title);
        TextView fg_physical_exercise_result_text = (TextView)rootView.findViewById(R.id.fg_physical_exercise_result_text);
        Button fg_physical_exercise_button = (Button)rootView.findViewById(R.id.fg_physical_exercise_button);
        fg_physical_exercise_button.setOnClickListener(this::onClick);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        userRef = rootRef.child("Users").child(userId);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);

        triggerEventListener = new TriggerEventListener() {
            @Override
            public void onTrigger(TriggerEvent event) {
                fg_physical_exercise_result_text.setText("Exercising!");
                exercisecheck = 1;
            }
        };

        sensorManager.requestTriggerSensor(triggerEventListener, sensor);

        return rootView;
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor", "UseCompatLoadingForColorStateLists", "NonConstantResourceId", "SetTextI18n"})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fg_physical_exercise_button:
                if (exercisecheck == 1) {
                    userRef.child("progress").setValue(ServerValue.increment(Long.valueOf(30)));
                    ((DataSite) getActivity().getApplication()).getExercisePoints();
                    ((DataSite) getActivity().getApplication()).setExercisePoints(30);
                    Fragment fr2 = new ProgressFragment();
                    FragmentManager fm2 = getFragmentManager();
                    FragmentTransaction fragmentTransaction2 = fm2.beginTransaction();
                    fragmentTransaction2.replace(R.id.fg_physical_exercise_container, fr2);
                    fragmentTransaction2.commit();
                } else {
                    ((DataSite) getActivity().getApplication()).getExercisePoints();
                    ((DataSite) getActivity().getApplication()).setExercisePoints(1);
                    Fragment fr2 = new ProgressFragment();
                    FragmentManager fm2 = getFragmentManager();
                    FragmentTransaction fragmentTransaction2 = fm2.beginTransaction();
                    fragmentTransaction2.replace(R.id.fg_physical_exercise_container, fr2);
                    fragmentTransaction2.commit();
                }
                break;
        }
    }
}