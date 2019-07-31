package com.gxjfict.sample.utils.network;

import com.gxjfict.sample.utils.Hawk_keys;
import com.orhanobut.hawk.Hawk;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by LiuYi on 2019-07-10.
 */
public class CookieSaveInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            // JSESSIONID=039F070EA0619D6AF4370DE6E8CD3189; Path=/smsjk; HttpOnly
            String setCookieString = originalResponse.header("Set-Cookie");
            if (null != setCookieString && setCookieString.contains("JSESSIONID")) {
                Hawk.put(Hawk_keys.KEY_SESSION, setCookieString);
            }
        }
        return originalResponse;
    }
}
