package com.gxjfict.sample.utils.network;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by LiuYi on 2018/12/28.
 */
public class NetWork {
    private static NetWork mInstance=null;

    private IHttpService mHttpService;
    private NetWork()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("http://www.baidu.com/")
                //设置内容格式,这种对应的数据返回值是String类型
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mHttpService = retrofit.create(IHttpService.class);
    }

    public static synchronized NetWork getInstance()
    {
      if (mInstance==null){
          mInstance=new NetWork();
      }
      return mInstance;
    }

    public Observable<String> get(String url) {
        return mHttpService.get(url);
    }

}
