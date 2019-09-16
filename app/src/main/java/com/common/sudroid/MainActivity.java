package com.common.sudroid;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.common.baselib.base.BaseFragmentActivity;
import com.common.baselib.base.BaseHomePagerAdapter;
import com.common.baselib.base.BaseViewPagerAdapter;
import com.common.baselib.utils.AntiShake.AntiShake;
import com.common.baselib.utils.statusbar.StatusBarUtils;
import com.common.baselib.view.viewpager.BottomNavigationViewHelper;
import com.common.baselib.view.viewpager.SuperViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.viewpager.widget.ViewPager;

/**
 * 首页-主界面
 */
public class MainActivity extends BaseFragmentActivity {

    private SuperViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    private RelativeLayout rootView;

    @Override
    public boolean enableSlideClose() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置状态栏颜色
        StatusBarUtils.setStatusBarDarkTheme(this, true);
        if (savedInstanceState != null) {
            return;
        }

        rootView = (RelativeLayout) findViewById(R.id.container);
        viewPager = (SuperViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setItemIconTintList(null);

        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    //重置
                    if (AntiShake.check(item.getItemId())) return false;
                    switch (item.getItemId()) {
                        case R.id.item_home:
                            viewPager.setCurrentItem(0, false);
                            break;
                        case R.id.item_inves:
                            viewPager.setCurrentItem(1, false);
                            break;
                        case R.id.item_mine:
                            viewPager.setCurrentItem(2, false);
                            break;
                    }
                    return false;
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(2);


    }

    /**
     * 初始化主界面
     */
    private void setupViewPager(ViewPager viewPager) {
        BaseViewPagerAdapter adapter = null;
        if (adapter == null) {
            adapter = new BaseViewPagerAdapter(getSupportFragmentManager());
            adapter.ClearFragmentList();
            adapter.addFragment(MainFrag.newInstance("1"));
            adapter.addFragment(MainFrag.newInstance("2"));
            adapter.addFragment(MainFrag.newInstance("3"));
            viewPager.setAdapter(adapter);
        }
    }
}
