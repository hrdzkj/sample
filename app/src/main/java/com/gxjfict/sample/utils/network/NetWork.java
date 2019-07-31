package com.gxjfict.sample.utils.network;

import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


import com.gxjfict.sample.MyApplication;
import com.gxjfict.sample.utils.Hawk_keys;
import com.gxjfict.sample.utils.JsonData;
import com.orhanobut.hawk.Hawk;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.gxjfict.sample.business.login.LoginActivity.IS_LOGIN;

/**
 * Created by LiuYi on 2018/12/28.
 */
public class NetWork {
    private static NetWork mInstance = null;
    private IHttpService mHttpService;
    private final Charset UTF8 = Charset.forName("UTF-8");
    private Map<String, String> mHeaderParaList = new HashMap<>();

    private NetWork() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // builder.addInterceptor(new CookieSaveInterceptor()).addInterceptor(new CookieSetInterceptor());
        Retrofit retrofit = new Retrofit.Builder().client(builder.build()).baseUrl(HttpConst.BASEURL)
                //设置内容格式,这种对应的数据返回值是String类型
                .addConverterFactory(ScalarsConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        mHttpService = retrofit.create(IHttpService.class);
    }

    public static synchronized NetWork getInstance() {
        if (mInstance == null) {
            mInstance = new NetWork();
        }
        return mInstance;
    }

    public Observable<String> post(String path, Map<String, String> para) {
        return mHttpService.postMap(path, para);
    }

    public NetWork clearCookie()
    {
        mHeaderParaList.clear();
        return this;
    }

}

