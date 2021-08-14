package com.example.vit.Onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.vit.Authentication.MainActivityHandler;
import com.example.vit.MainActivity;
import com.example.vit.Onboarding.OnBoardingFragment1;
import com.example.vit.Onboarding.OnBoardingFragment2;
import com.example.vit.Onboarding.OnBoardingFragment3;
import com.example.vit.R;

public class IntroductoryActivity extends AppCompatActivity {
    private static final  int NUM_PAGES =3;
    private ViewPager viewPager;
    private ScreenSlidePagerAdapter pagerAdapter;
    String prevStarted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);



        ViewPager viewPager = findViewById(R.id.pager);
        ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }


    private static class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new OnBoardingFragment1();

                case 1:
                    return new OnBoardingFragment2();

                case 2:
                    return new OnBoardingFragment3();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreference = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if(!sharedPreference.getBoolean(prevStarted,false)) {
            SharedPreferences.Editor editor = sharedPreference.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
        }
        else{
            Intent intent = new Intent(this, MainActivityHandler.class);
            startActivity(intent);
        }
    }
}