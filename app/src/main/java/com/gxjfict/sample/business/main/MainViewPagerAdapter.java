package com.gxjfict.sample.business.main;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by LiuYi on 2018/8/29.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mListFragment;

    public MainViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void setFragment(List<Fragment> listFragment) {
        this.mListFragment = listFragment;
    }

    @Override
    public Fragment getItem(int posotion) {
        return mListFragment.get(posotion);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;// 返回这个表示该对象已改变,需要刷新
        // return POSITION_UNCHANGED;// 反之不刷新
    }

    @Override
    public int getCount() {
        return mListFragment.size();
    }

}