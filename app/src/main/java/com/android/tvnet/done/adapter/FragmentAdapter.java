package com.android.tvnet.done.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.tvnet.R;
import com.android.tvnet.done.fragment.DoneTaskFragment;
import com.android.tvnet.done.fragment.StatisticsFragment;

public class FragmentAdapter extends FragmentPagerAdapter {
    private Context context;

    public FragmentAdapter(FragmentManager childFragmentManager, Context context) {
        super(childFragmentManager);
        this.context =context;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            default:
            case 0:
                fragment = DoneTaskFragment.newInstance();
                break;
            case 1:
                fragment = StatisticsFragment.newInstance();
                break;

        }
        return fragment;

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return context.getResources().getString(R.string.done);
            case 1:
                return context.getResources().getString(R.string.statistics);

            default:
                return "";
        }
    }

}
