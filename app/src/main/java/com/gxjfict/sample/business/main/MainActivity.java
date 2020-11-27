package com.gxjfict.sample.business.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.blankj.utilcode.util.AppUtils;
import com.gxjfict.sample.R;
import com.gxjfict.sample.dialog.UpdateVersionDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private ImageView mImageViewHome, mImageViewMine;
    private TextView mTextTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        getRemoteApkVersion();
    }


    private void initView() {
        mTextTitle = findViewById(R.id.TextViewTopTitle);
        mViewPager = findViewById(R.id.viewPager);
        mImageViewHome = findViewById(R.id.imageViewHome);
        mImageViewMine = findViewById(R.id.imageViewMine);
        mImageViewHome.setOnClickListener(this);
        mImageViewMine.setOnClickListener(this);
        mTextTitle.setText("首页");
        findViewById(R.id.ImageViewGoBack).setVisibility(View.INVISIBLE);
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(MeFragment.newInstance());
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        adapter.setFragment(fragmentList);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        //设置页面切换更换页面
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mImageViewHome.setImageResource(R.drawable.home_hover);
                        mImageViewMine.setImageResource(R.drawable.mine_normal);
                        mTextTitle.setText("首页");
                        break;

                    case 1:
                        mImageViewHome.setImageResource(R.drawable.home_normal);
                        mImageViewMine.setImageResource(R.drawable.mine_hover);
                        mTextTitle.setText("我的");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewHome:
                mViewPager.setCurrentItem(0);
                break;

            case R.id.imageViewMine:
                mViewPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
    }

    @SuppressLint("CheckResult")
    private void getRemoteApkVersion() {
        String downApkUrl = "http://202.103.240.156:8090/ICRCOffice/tools/redCrossApp.apk";
        int versionCode =2;
        String versionName = "2.0";
        if (versionCode > AppUtils.getAppVersionCode() && !TextUtils.isEmpty(downApkUrl)) {
            new UpdateVersionDialog.Builder(this).setVersionName(versionName).setUpdateAppUrl(downApkUrl).create().show();
        }
    }


}

