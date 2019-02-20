package com.gxjfict.sample.business.login;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.gxjfict.sample.R;
import com.gxjfict.sample.base.BaseActivity;
import com.gxjfict.sample.business.main.MainActivity;
import com.gxjfict.sample.utils.Hawk_keys;
import com.gxjfict.sample.utils.JsonData;
import com.gxjfict.sample.utils.ToastUtil;
import com.gxjfict.sample.utils.network.HttpConst;
import com.gxjfict.sample.utils.network.NetWork;
import com.orhanobut.hawk.Hawk;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.gxjfict.sample.utils.network.HttpConst.REQUEST_SUCCESS;

/**
 * Created by LiuYi on 2018/8/29.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText mTextUserName;
    private EditText mTextPassword;
    private TextView mTextViewTopTitle;
    private ImageView mImageViewGoBack;


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
                if (TextUtils.isEmpty(mTextUserName.getText().toString())) {
                    ToastUtil.showShort("请输入用户名");
                    return;
                }

                if (TextUtils.isEmpty(mTextPassword.getText().toString())) {
                    ToastUtil.showShort("请输入密码");
                    return;
                }

                doLogin();
                break;
            default:
                break;
        }
    }


    @SuppressLint("CheckResult")
    private void doLogin() {
        Map<String, String> para = new HashMap<>();
        para.put("studentAccount", mTextUserName.getText().toString());
        para.put("studentPwd", mTextPassword.getText().toString());
        unsubscribe();
        mDisposable = NetWork.getInstance().post(HttpConst.APP_LOGIN, para).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
            JsonData result = JsonData.create(s);
            if (REQUEST_SUCCESS.equals(result.optString("code"))) {
                Hawk.put(Hawk_keys.KEY_TOKEN,result.optString("data"));
                toMainActivity();
            } else {
                ToastUtil.showShort(result.optString("msg"));
            }
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
}
