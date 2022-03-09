package com.example.ssresilience;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SocialFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SocialFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String goal;

    public static String FACEBOOK_URL = "https://www.facebook.com/ssresilienceapp";
    public static String FACEBOOK_PAGE_ID = "SSResilience App";

    private Button facebook, messenger, instagram, twitter;

    public SocialFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SocialFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SocialFragment newInstance(String param1, String param2) {
        SocialFragment fragment = new SocialFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_social, container, false);

        facebook = (Button)rootView.findViewById(R.id.fg_social_facebook);
        facebook.setOnClickListener(this::onClick);
        twitter = (Button)rootView.findViewById(R.id.fg_social_twitter);
        twitter.setOnClickListener(this::onClick);
        instagram = (Button)rootView.findViewById(R.id.fg_social_instagram);
        instagram.setOnClickListener(this::onClick);
        messenger = (Button)rootView.findViewById(R.id.fg_social_messenger);
        messenger.setOnClickListener(this::onClick);

        return rootView;
    }

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor", "UseCompatLoadingForColorStateLists", "NonConstantResourceId", "SetTextI18n"})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fg_social_facebook:
                Intent intent5 = null;
                try {
                    getActivity().getPackageManager().getPackageInfo("com.faceook.android", 0);
                    intent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("facebook://"));
                    intent5.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    intent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com"));
                }
                this.startActivity(intent5);
                break;
            case R.id.fg_social_twitter:
                Intent intent = null;
                try {
                    getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/"));
                }
                this.startActivity(intent);
                break;
            case R.id.fg_social_instagram:
                Intent intent3 = null;
                try {
                    getActivity().getPackageManager().getPackageInfo("com.instagram.android", 0);
                    intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("instagram://"));
                    intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com"));
                }
                this.startActivity(intent3);
                break;
            case R.id.fg_social_messenger:
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_SEND);
                intent2.putExtra(Intent.EXTRA_TEXT, "Hello");
                intent2.setType("text/plain");
                intent2.setPackage("com.facebook.orca");
                try
                {
                    startActivity(intent2);
                }
                catch (ActivityNotFoundException ex)
                {
                    Toast.makeText(getActivity(),
                            "Cannot open Facebook Messenger right now. Please try again later.",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}