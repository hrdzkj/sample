package com.gxjfict.sample.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.gxjfict.sample.R;
import com.gxjfict.sample.base.BaseActivity;
import com.gxjfict.sample.base.BaseViewModel;
import com.gxjfict.sample.utils.ToastUtil;
import com.gxjfict.sample.utils.network.NetWork;
import com.gxjfict.sample.utils.network.Resource;
import com.gxjfict.sample.viewmodel.SampleViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    private SampleViewModel mBaseViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBaseViewModel = ViewModelProviders.of(this).get(SampleViewModel.class);
        mBaseViewModel.asLiveData().observe(this, resource -> {
            Object object = resource.getData();
            if (object!=null){
                ToastUtil.showShort(object.toString());
            }
        });

        findViewById(R.id.sample_text).setOnClickListener(view -> {
            //mBaseViewModel.getBaidu();
        });
    }



}
