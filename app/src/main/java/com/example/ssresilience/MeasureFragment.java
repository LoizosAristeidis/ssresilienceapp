package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    private int check;
    private String goal, dbgoal, dbmeasure;
    private Button fg_measure_button_go;
    private MediaRecorder mRecorder = null;
    private String updateD;
    private int checkifmeasured;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef, dbReference;
    private boolean allowRefresh = false;

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
    public void onResume() {
        super.onResume();
        //Initialize();
        if(allowRefresh){
            allowRefresh=false;
            Fragment fr = new ProgressFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.fg_measure_container, fr);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!allowRefresh)
            allowRefresh = true;
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

        check = ((DataSite)getActivity().getApplication()).getCheck();

        // Retrieve the selected Goal from the DataSite Class
        goal = ((DataSite)getActivity().getApplication()).getGoal();

        // Initialize the Fragment's TextViews
        TextView fg_measure_title = (TextView)rootView.findViewById(R.id.fg_measure_title);
        TextView fg_measure_text = (TextView)rootView.findViewById(R.id.fg_measure_text);
        Button fg_measure_button_go = (Button)rootView.findViewById(R.id.fg_measure_button_go);
//        fg_measure_button_go.setOnClickListener(this::onClick);

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
                    dbmeasure = userProfile.measureme;
                    Calendar c = Calendar.getInstance();
                    System.out.println("Current time => "+ c.getTime());
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    updateD = df.format(c.getTime());
                    // Fill the Fragment's TextViews according to the selected Goal
                    if (dbgoal != null) {
                        if (dbgoal.equals("Socialize More")) {
                            fg_measure_title.setText("Κοινωνικοποίηση");
                            fg_measure_text.setText("Μετρήστε το επίπεδο θορύβου του περιβάλλοντός σας, για να ενημερώσετε την εφαρμογή ότι συναναστρέφεστε με ανθρώπους.");
                        }
                        if (dbgoal.equals("Enhance Study Motives")) {
                            fg_measure_title.setText("Κίνητρα για Μελέτη");
                            fg_measure_text.setText("Επιτρέψτε στην εφαρμογή να υπολογίσει το επίπεδο άγχους σας, κάνοντας το τεστ. Θυμηθείτε, ένα υψηλότερο επίπεδο άγχους οδηγεί σε χαμηλότερα κίνητρα μελέτης.");
                        }
                        if (dbgoal.equals("Physical Exercise")) {
                            fg_measure_title.setText("Φυσική Άσκηση");
                            fg_measure_text.setText("Ελέγξτε την τρέχουσα κατάσταση φυσικής δραστηριότητας χρησιμοποιώντας τον αισθητήρα επιταχυνσιόμετρου της συσκευής σας.");
                        }
                        fg_measure_button_go.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (dbgoal.equals("Socialize More")) {
                                    if (dbmeasure.equals("yes")) {
                                        Toast.makeText(getActivity(), "Έχετε ήδη μετρήσει το επίπεδο θορύβου.\n\nΠαρακαλώ προσδιορίστε διαφορετικό Στόχο και δοκιμάστε ξανά!",
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        Intent intent = new Intent(getActivity(), AudioRecordTest.class);
                                        startActivity(intent);
                                    }
                                }
                                if (dbgoal.equals("Enhance Study Motives")) {
                                    if (dbmeasure.equals("yes")) {
                                        Toast.makeText(getActivity(), "Έχετε ήδη κάνει το τεστ μέτρησης άγχους.\n\nΠαρακαλώ προσδιορίστε διαφορετικό Στόχο και δοκιμάστε ξανά!",
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        Fragment fr = new GadTest();
                                        FragmentManager fm = getFragmentManager();
                                        FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                        fragmentTransaction.replace(R.id.fg_measure_container, fr);
                                        fragmentTransaction.commit();
                                    }
                                }
                                if (dbgoal.equals("Physical Exercise")) {
                                    if (dbmeasure.equals("yes")) {
                                        Toast.makeText(getActivity(), "Έχετε ήδη ελέγξε την κατάσταση φυσικής δραστηριότητάς σας.\n\nΠαρακαλώ προσδιορίστε διαφορετικό Στόχο και δοκιμάστε ξανά!",
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        Fragment fr = new PhysicalExercise();
                                        FragmentManager fm = getFragmentManager();
                                        FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                        fragmentTransaction.replace(R.id.fg_measure_container, fr);
                                        fragmentTransaction.commit();
                                    }
                                }
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "Παρακαλώ επιλέγξτε έναν Στόχο πρώτα.",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(getActivity(), "Σφάλμα κατά τη λήψη στοιχείων χρήστη.",
                        Toast.LENGTH_LONG).show();
            }

        });

        return rootView;
    }
}