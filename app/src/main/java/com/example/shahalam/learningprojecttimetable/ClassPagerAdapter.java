package com.example.shahalam.learningprojecttimetable;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

class ClassPagerAdapter extends FragmentPagerAdapter {


    public ClassPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new SundayFragment();
            case 1: return new MondayFragment();
            case 2: return new TuesdayFragment();
            case 3: return new WednesdayFragment();
            case 4: return new ThursdayFragment();
            case 5: return new FridayFragment();
            case 6: return new SaturdayFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0: return "Sunday";
            case 1: return "Monday";
            case 2: return "TuesDay";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
            default: return null;
        }
    }
}
