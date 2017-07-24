package com.esp.hangul.Home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.v4.app.FragmentPagerAdapter;

public class BottomPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {



    public BottomPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new DictionaryFragment();
            case 1: return new StudyFragment();
            case 2: return new QuizFragment();
            default: return new StudyFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);

    }
}
