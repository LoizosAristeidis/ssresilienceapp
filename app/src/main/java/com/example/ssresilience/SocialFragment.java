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

    private Button fg_social_sharebtn, facebook_ssr, messenger_ssr, instagram_ssr, twitter_ssr;

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

        fg_social_sharebtn = (Button)rootView.findViewById(R.id.fg_social_sharebtn);
        fg_social_sharebtn.setOnClickListener(this::onClick);
        facebook_ssr = (Button)rootView.findViewById(R.id.fg_social_facebook_ssr);
        facebook_ssr.setOnClickListener(this::onClick);
        twitter_ssr = (Button)rootView.findViewById(R.id.fg_social_twitter_ssr);
        twitter_ssr.setOnClickListener(this::onClick);
        instagram_ssr = (Button)rootView.findViewById(R.id.fg_social_instagram_ssr);
        instagram_ssr.setOnClickListener(this::onClick);
        messenger_ssr = (Button)rootView.findViewById(R.id.fg_social_messenger_ssr);
        messenger_ssr.setOnClickListener(this::onClick);

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
            case R.id.fg_social_sharebtn:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "This is the text that will be shared.");
                startActivity(Intent.createChooser(sharingIntent,"Share using"));
                break;
            case R.id.fg_social_facebook_ssr:
                try {
                    Intent intent7 = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/106751275295791"));
                    startActivity(intent7);
                } catch(Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/ssresilienceapp")));
                }
                break;
            case R.id.fg_social_twitter_ssr:
                Intent intent8 = null;
                try {
                    getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent8 = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=ssresilienceapp"));
                    intent8.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (Exception e) {
                    intent8 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/ssresilienceapp"));
                }
                this.startActivity(intent8);
                break;
            case R.id.fg_social_instagram_ssr:
                Uri uri = Uri.parse("http://instagram.com/_u/ssresilience.app");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/ssresilience.app")));
                }
                break;
            case R.id.fg_social_messenger_ssr:
                Intent intent10 = new Intent();
                intent10.setAction(Intent.ACTION_VIEW);
                intent10.setPackage("com.facebook.orca");
                intent10.setData(Uri.parse("https://m.me/"+"106751275295791"));
                startActivity(intent10);
                break;
        }
    }
}