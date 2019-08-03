package com.gxjfict.sample.utils.network;

import android.content.Intent;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


import com.gxjfict.sample.MyApplication;
import com.gxjfict.sample.utils.Hawk_keys;
import com.gxjfict.sample.utils.JsonData;
import com.gxjfict.sample.utils.ToastUtil;
import com.orhanobut.hawk.Hawk;

import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
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

    private class DownloadInfo {
        private long fileSize;//单位 byte
        private long currentSize;//当前下载大小
        private int progress;//当前下载进度

        public long getFileSize() {
            return fileSize;
        }

        public void setFileSize(long fileSize) {
            this.fileSize = fileSize;
        }

        public long getCurrentSize() {
            return currentSize;
        }

        public void setCurrentSize(long currentSize) {
            this.currentSize = currentSize;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

    }

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



    // https://blog.csdn.net/u011082160/article/details/81233756
    public void download(String url,String filePath,DownloadListener downloadListener){
         mHttpService.download(url).subscribeOn(Schedulers.io()).flatMap((Function<ResponseBody, ObservableSource<DownloadInfo>>) responseBody -> {
            return Observable.create(emitter -> {
                InputStream inputStream = null;

                FileOutputStream fos = null;
                DownloadInfo downloadInfo = new DownloadInfo();
                try {
                    byte[] buf = new byte[2048];
                    long resLength = responseBody.contentLength();
                    long totalRead = 0;
                    int len = 0;
                    inputStream = responseBody.byteStream();
                    downloadInfo.setFileSize(resLength);
                    fos = new FileOutputStream(filePath);
                    while ((len = inputStream.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        totalRead += len;
                        int progress = (int) (totalRead * 100 / resLength);

                        //读到数据，更新进度
                        if (len > 0) {
                            downloadInfo.setProgress(progress);
                            downloadInfo.setCurrentSize(totalRead);
                            emitter.onNext(downloadInfo);
                        }
                    }
                    fos.flush();
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                } finally {
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

        }).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<DownloadInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(DownloadInfo downloadInfo) {
                 if (downloadListener!=null){
                     downloadListener.onProgress(downloadInfo.getProgress());
                 }
            }

            @Override
            public void onError(Throwable e) {
                if (downloadListener!=null){
                    downloadListener.onFailed(e);
                }
            }

            @Override
            public void onComplete() {
                if (downloadListener!=null){
                    downloadListener.onFinish();
                }
            }
        });
    }

    public NetWork clearCookie()
    {
        mHeaderParaList.clear();
        return this;
    }

}

