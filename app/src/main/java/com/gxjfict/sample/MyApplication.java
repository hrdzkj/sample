package com.gxjfict.sample;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.gxjfict.sample.R;
import com.gxjfict.sample.utils.ActivityLifecycleCallbacksImp;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * Created by LiuYi on 2018/12/28.
 */
public class MyApplication extends Application {
    private static Application instance;
    //static 代码段可以防止内存泄露
    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull com.scwang.smartrefresh.layout.api.RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.color_3F51B5, android.R.color.white);
                //指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });

        //设置脚布局样式,全局有效
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance =this;

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksImp());

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


}
