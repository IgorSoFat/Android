package com.example.asus.tp1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MainFragment();
        } else if (position == 1) {
            return new AddFragment();
        } else if (position == 2) {
            return new ListFragment();
        } else if (position ==3 ){
            return new PositionFragment();
        } else {
            return new ContactFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

}