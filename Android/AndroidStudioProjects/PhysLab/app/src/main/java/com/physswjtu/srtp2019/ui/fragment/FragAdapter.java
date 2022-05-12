/*
 * Copyright (c) 2019. 西南交大应用物理系韩宝佳
 */

package com.physswjtu.srtp2019.ui.fragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.physswjtu.srtp2019.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 由 84697 创建
 * 日期为 2019/7/2
 * 工程 PhysLab
 */
public class FragAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private Context context;

    public FragAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        fragments.add(TimeTableFragment.newInstance(getPageTitle(0), new Date(System.currentTimeMillis()).toString()));
        fragments.add(CourseSelectFragment.newInstance(getPageTitle(1)));
        fragments.add(TimeTableFragment.newInstance(getPageTitle(2), new Date(System.currentTimeMillis()).toString()));
        fragments.add(ExperimentsFragment.newInstance(getPageTitle(3), new Date(System.currentTimeMillis()).toString()));
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return FragmentPagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public String getPageTitle(int position) {
        String[] titles = {context.getResources().getString(R.string.menu_time_table), context.getResources().getString(R.string.menu_course_selection), context.getResources().getString(R.string.menu_notice), context.getResources().getString(R.string.menu_experiment)};
        return titles[position];
    }


}
