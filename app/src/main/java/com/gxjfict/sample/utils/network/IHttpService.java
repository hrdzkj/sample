package com.gxjfict.sample.utils.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by LiuYi on 2018/12/28.
 */
public interface IHttpService {
    @GET
    Observable<String> get(@Url String url);
}
