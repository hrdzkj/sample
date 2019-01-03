package com.gxjfict.sample.viewmodel;

import com.gxjfict.sample.base.BaseViewModel;
import com.gxjfict.sample.utils.network.NetWork;
import com.gxjfict.sample.utils.network.Resource;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LiuYi on 2019/1/3.
 */
public class SampleViewModel extends BaseViewModel {

    public void getBaidu()
    {
        unsubscribe();
        mDisposable = NetWork.getInstance().get("http://wwww.baidu.com")
                .flatMap((Function<String, Observable<String>>) s -> NetWork.getInstance().get("http://wwww.baidu.com"))
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Resource<String> value = Resource.loading(s);
                        mMutableLiveData.postValue(value);
                    }
                });
    }

}
