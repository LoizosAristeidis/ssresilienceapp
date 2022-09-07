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
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GadTestResults#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GadTestResults extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int gadpoints;

    public GadTestResults() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GadTestResults.
     */
    // TODO: Rename and change types and number of parameters
    public static GadTestResults newInstance(String param1, String param2) {
        GadTestResults fragment = new GadTestResults();
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
        View rootView = inflater.inflate(R.layout.fragment_gad_test_results, container, false);

        gadpoints = ((DataSite) getActivity().getApplication()).getGadPoints();

        Button gad_result_back = (Button)rootView.findViewById(R.id.gad_result_back);
        TextView gad_result_text = (TextView)rootView.findViewById(R.id.gad_result_text);
        gad_result_back.setOnClickListener(this::onClick);

        if (gadpoints <= 9) {
            gad_result_text.setText("Έχετε χαμηλό επίπεδο άγχους που δεν θα παρεμπόδιζε τη μελέτη σας.\n\nΣυνεχίστε έτσι!");
        }
        if ((gadpoints > 9)) {
            gad_result_text.setText("Μπορεί να νιώθετε ένα επίπεδο άγχους αυτή τη στιγμή.\n\nΔοκιμάστε μια άσκηση αναπνοής για να χαλαρώσετε πριν μελετήσετε.");
        }

        return rootView;
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gad_result_back:
                Fragment fr = new ProgressFragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fg_gad_test_result_container, fr);
                fragmentTransaction.commit();
                ((InitialScreen)getActivity()).setNavItem();
                break;
        }
    }
}