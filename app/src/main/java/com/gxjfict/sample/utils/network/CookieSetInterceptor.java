package com.gxjfict.sample.utils.network;

import android.util.Log;

import com.gxjfict.msgfilter.utils.Hawk_keys;
import com.orhanobut.hawk.Hawk;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.gxjfict.msgfilter.utils.network.HttpConst.APP_LOGIN;

/**
 * Created by LiuYi on 2019-07-10.
 */
public class CookieSetInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Log.v("--->",original.url().encodedPath());
       String path= original.url().encodedPath();
       // original.method();
        Response response;
        if (Hawk.contains(Hawk_keys.KEY_SESSION) && !path.contains(APP_LOGIN)) {
            Request request = original.newBuilder().header("Cookie", Hawk.get(Hawk_keys.KEY_SESSION)).build();
            response = chain.proceed(request);
        } else {
            response = chain.proceed(original);
        }
        return response;

    }
}
