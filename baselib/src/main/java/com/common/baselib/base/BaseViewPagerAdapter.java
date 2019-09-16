package com.common.baselib.base;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by Sudroid on 2017/6/13.
 */

public class BaseViewPagerAdapter extends FragmentPagerAdapter {

    public static final List<Fragment> mFragmentList = new ArrayList<>();

    public BaseViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }


    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((Fragment) object).getView();
    }
    public void ClearFragmentList(){
        mFragmentList.clear();
    }
}
