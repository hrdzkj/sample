package com.gxjfict.sample.utils.network;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by LiuYi on 2018/12/28.
 */
public interface IHttpService {

/*
    @FormUrlEncoded
    @POST("common/studentLogin/")
    Observable<String> postMap(@FieldMap() Map<String, String> params);
    */

    @FormUrlEncoded
    @POST
    Observable<String> postMap(@Url String url,@FieldMap() Map<String, String> params);


    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
