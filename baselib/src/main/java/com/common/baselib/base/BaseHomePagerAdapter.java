package com.common.baselib.base;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * Created by HugoXie on 16/7/9.
 * <p>
 * Email: Hugo3641@gamil.com
 * GitHub: https://github.com/xcc3641
 * Info:
 */
public class BaseHomePagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public BaseHomePagerAdapter(FragmentManager fm) {
        super(fm);
        notifyDataSetChanged();
    }

    public BaseHomePagerAdapter(FragmentManager fm, TabLayout tabLayout) {
        super(fm);
        notifyDataSetChanged();
    }

    public void addTab(Fragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);

    }

    /**
     * Return the Fragment associated with a specified position.
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return fragments.get(position).hashCode();
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


}
