package com.gxjfict.sample.utils.network;

import java.util.Map;


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

/**
 * Created by LiuYi on 2018/12/28.
 */
public class NetWork {
    private static NetWork mInstance=null;
    private IHttpService mHttpService;
    private final Charset UTF8 = Charset.forName("UTF-8");
    
    private NetWork()
    {
        //添加Header的方法
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            Request original = chain.request();
            Response response;
            if (Hawk.contains(Hawk_keys.KEY_TOKEN)) {
                // add something
                Request request = original.newBuilder().header("Authorization", Hawk.get(Hawk_keys.KEY_TOKEN)).build();
                response= chain.proceed(request);
            } else {
                response=chain.proceed(original);
            }


            // 目前是统一处理登录失效，如果要解密，怎么处理呢 同样 ResponseBody.create https://www.jianshu.com/p/83caa619b219
            ResponseBody responseBody = response.body();
            String rBody = null;
            if(responseBody!=null) {
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    try {
                        charset = contentType.charset(UTF8);
                    } catch (UnsupportedCharsetException e) {
                        e.printStackTrace();
                    }
                }
                rBody = buffer.clone().readString(charset);
                JsonData jsonData = JsonData.create(rBody);
                if (jsonData.optString("msg").contains("尚未登录")){
                    Intent intent = new Intent();
                    intent.setAction("EXAM_LOGIN_OUT");
                    MyApplication.getInstance().sendBroadcast(intent);
                }

            }
            return response;
        });

        Retrofit retrofit = new Retrofit.Builder().client(builder.build()).baseUrl(HttpConst.BASEURL)
                //设置内容格式,这种对应的数据返回值是String类型
                .addConverterFactory(ScalarsConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        mHttpService = retrofit.create(IHttpService.class);
    }

    public static synchronized NetWork getInstance()
    {
      if (mInstance==null){
          mInstance=new NetWork();
      }
      return mInstance;
    }

    public Observable<String> post(String path,Map<String,String>para) {
        return mHttpService.postMap(path,para);
    }

}

