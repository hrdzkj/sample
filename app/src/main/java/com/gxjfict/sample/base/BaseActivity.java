package com.gxjfict.sample.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import com.gxjfict.sample.R;

import io.reactivex.disposables.Disposable;

/**
 * Created by LiuYi on 2018/12/28.
 * 基础类的Activity,封装了共用的方法，具体包括：吐司，转场动画，Rxjava取消订阅；
 * 同时初始化好了加载菊花对话框对象，同时隐藏了标题栏。
 */
public class BaseActivity extends AppCompatActivity {

    private Toast mToast = null;
    protected Disposable mDisposable;

    /**
     * 显示吐司
     * @param hint 显示的提示信息
     */
    protected void showToast(String hint) {
        if (mToast != null) {
            mToast.setText(hint);
        } else {
            mToast = Toast.makeText(this, hint, Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * 对RxJava的Disposable进行取消订阅，防止内存泄漏
     */
    protected void unsubscribe() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.right_in,R.anim.right_out);
    }

    @Override
    protected void onDestroy() {
        if (mToast!=null){
            mToast.cancel();
        }
        unsubscribe();
        super.onDestroy();
    }
}
