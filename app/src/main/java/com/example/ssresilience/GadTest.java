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
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GadTest#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GadTest extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button row1_btn1;
    private int gadpoints = 0;

    public GadTest() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GadTest.
     */
    // TODO: Rename and change types and number of parameters
    public static GadTest newInstance(String param1, String param2) {
        GadTest fragment = new GadTest();
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
        View rootView = inflater.inflate(R.layout.fragment_gad_test, container, false);

        Button info_btn = (Button)rootView.findViewById(R.id.info_btn);
        info_btn.setOnClickListener(this::onClick);

        ToggleButton row1_btn1 = (ToggleButton)rootView.findViewById(R.id.row1_btn1);
        row1_btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 0;
                }
            }
        });

        ToggleButton row1_btn2 = (ToggleButton)rootView.findViewById(R.id.row1_btn2);
        row1_btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 1;
                }
            }
        });

        ToggleButton row1_btn3 = (ToggleButton)rootView.findViewById(R.id.row1_btn3);
        row1_btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 2;
                }
            }
        });

        ToggleButton row1_btn4 = (ToggleButton)rootView.findViewById(R.id.row1_btn4);
        row1_btn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 3;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row2_btn1 = (ToggleButton)rootView.findViewById(R.id.row2_btn1);
        row2_btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 0;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row2_btn2 = (ToggleButton)rootView.findViewById(R.id.row2_btn2);
        row2_btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 1;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row2_btn3 = (ToggleButton)rootView.findViewById(R.id.row2_btn3);
        row2_btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 2;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row2_btn4 = (ToggleButton)rootView.findViewById(R.id.row2_btn4);
        row2_btn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 3;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row3_btn1 = (ToggleButton)rootView.findViewById(R.id.row3_btn1);
        row3_btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 0;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row3_btn2 = (ToggleButton)rootView.findViewById(R.id.row3_btn2);
        row3_btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 1;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row3_btn3 = (ToggleButton)rootView.findViewById(R.id.row3_btn3);
        row3_btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 2;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row3_btn4 = (ToggleButton)rootView.findViewById(R.id.row3_btn4);
        row3_btn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 3;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row4_btn1 = (ToggleButton)rootView.findViewById(R.id.row4_btn1);
        row4_btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 0;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row4_btn2 = (ToggleButton)rootView.findViewById(R.id.row4_btn2);
        row4_btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 1;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row4_btn3 = (ToggleButton)rootView.findViewById(R.id.row4_btn3);
        row4_btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 2;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row4_btn4 = (ToggleButton)rootView.findViewById(R.id.row4_btn4);
        row4_btn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 3;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row5_btn1 = (ToggleButton)rootView.findViewById(R.id.row5_btn1);
        row5_btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 0;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row5_btn2 = (ToggleButton)rootView.findViewById(R.id.row5_btn2);
        row5_btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 1;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row5_btn3 = (ToggleButton)rootView.findViewById(R.id.row5_btn3);
        row5_btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 2;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row5_btn4 = (ToggleButton)rootView.findViewById(R.id.row5_btn4);
        row5_btn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 3;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row6_btn1 = (ToggleButton)rootView.findViewById(R.id.row6_btn1);
        row6_btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 0;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row6_btn2 = (ToggleButton)rootView.findViewById(R.id.row6_btn2);
        row6_btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 1;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row6_btn3 = (ToggleButton)rootView.findViewById(R.id.row6_btn3);
        row6_btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 2;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row6_btn4 = (ToggleButton)rootView.findViewById(R.id.row6_btn4);
        row6_btn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 3;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row7_btn1 = (ToggleButton)rootView.findViewById(R.id.row7_btn1);
        row7_btn1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 0;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row7_btn2 = (ToggleButton)rootView.findViewById(R.id.row7_btn2);
        row7_btn2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 1;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row7_btn3 = (ToggleButton)rootView.findViewById(R.id.row7_btn3);
        row7_btn3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 2;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        ToggleButton row7_btn4 = (ToggleButton)rootView.findViewById(R.id.row7_btn4);
        row7_btn4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    gadpoints += 3;
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                }
            }
        });

        Button gad_submit = (Button)rootView.findViewById(R.id.gad_submit);
        gad_submit.setOnClickListener(this::onClick);

        return rootView;
    }

    @SuppressLint({"NonConstantResourceId", "UseCompatLoadingForDrawables"})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_btn:
                Fragment fr = new GadTestInfo();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.fg_gad_test_container, fr);
                fragmentTransaction.commit();
                break;
            case R.id.gad_submit:
                    ((DataSite) getActivity().getApplication()).getGadPoints();
                    ((DataSite) getActivity().getApplication()).setGadPoints(gadpoints);
                    Fragment fr2 = new ProgressFragment();
                    FragmentManager fm2 = getFragmentManager();
                    FragmentTransaction fragmentTransaction2 = fm2.beginTransaction();
                    fragmentTransaction2.replace(R.id.fg_gad_test_container, fr2);
                    fragmentTransaction2.commit();
                break;
            default:
                break;
        }
    }
}