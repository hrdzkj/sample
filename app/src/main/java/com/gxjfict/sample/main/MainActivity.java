package com.gxjfict.sample.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.gxjfict.sample.R;
import com.gxjfict.sample.base.BaseActivity;
import com.gxjfict.sample.utils.network.NetWork;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.sample_text).setOnClickListener(view -> {
            retrofitRxjava();
        });
    }


    @SuppressLint("CheckResult")
    private void retrofitRxjava() {
        Log.v("-------->","clickEvent is call");
        unsubscribe();
        mDisposable = NetWork.getInstance().get("http://wwww.baidu.com")
                .flatMap((Function<String, Observable<String>>) s -> NetWork.getInstance().get("http://wwww.baidu.com"))
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(s -> showToast("成功"), throwable -> showToast("网络异常"));
    }
}
