package com.gxjfict.sample.business.login;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.gxjfict.sample.R;
import com.gxjfict.sample.base.BaseActivity;
import com.gxjfict.sample.business.main.MainActivity;
import com.gxjfict.sample.utils.Hawk_keys;
import com.gxjfict.sample.utils.ToastUtil;
import com.gxjfict.sample.utils.network.HttpConst;
import com.gxjfict.sample.utils.network.NetWork;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LiuYi on 2018/8/29.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText mTextUserName;
    private EditText mTextPassword;
    private TextView mTextViewTopTitle;
    private ImageView mImageViewGoBack;

    public static boolean IS_LOGIN=false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //如果已经登录，则直接跳转main
        if (Hawk.contains(Hawk_keys.KEY_TOKEN)){
            toMainActivity();
            return;
        }
        initView();
    }

    protected void initView() {
        mTextViewTopTitle = findViewById(R.id.TextViewTopTitle);
        mImageViewGoBack = findViewById(R.id.ImageViewGoBack);
        mTextViewTopTitle.setText("登录");
        mTextUserName = findViewById(R.id.edtUserName);
        mTextPassword = findViewById(R.id.edtPassword);
        mImageViewGoBack.setVisibility(View.INVISIBLE);
        findViewById(R.id.btnLogin).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:

                //doLogin();
                testRxjava();
                break;
            default:
                break;
        }
    }


    @SuppressLint("CheckResult")
    private void doLogin() {
        unsubscribe();

        Map<String,String> para = new HashMap<>();
        para.put("txtAccount","011080606");
        para.put("txtPass","MTIzNDU2");
        para.put("txtVCode","147258369");
        mDisposable = NetWork.getInstance()
                .clearCookie()
                .post(HttpConst.APP_LOGIN, para)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                ToastUtil.showShort(s);
                toMainActivity();

        }, throwable -> {
            mLoadingDialog.dismiss();
            ToastUtil.showLong("网络异常");
            throwable.printStackTrace();
        });
    }


    private void toMainActivity()
    {
       startActivity(new Intent(this,MainActivity.class));
       finish();
    }


    private static String TAG="liuyi---->";
    @SuppressLint("CheckResult")
    private void testRxjava()
    {
        Observable<Integer> observable = Observable.create(emitter -> {
            Log.d(TAG, "onNext thread is : " + Thread.currentThread().getId());
            emitter.onNext(1);
        });



        Consumer<String> consumer = integer -> {
            Log.d(TAG, "Consumer thread is :" + Thread.currentThread().getId());
        };

        observable//.subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .map(integer -> {
                    Log.d(TAG, "map1 thread is :" + Thread.currentThread().getId());
                    return "2";
                })
                //.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(s->{
                    Log.d(TAG, "map2 thread is :" + Thread.currentThread().getId());
                    return "2";
                })
                .subscribeOn(Schedulers.io())
                //.observeOn(Schedulers.io())
                .subscribe(consumer);

    }
}
