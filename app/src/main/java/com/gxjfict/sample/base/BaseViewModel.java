package com.gxjfict.sample.base;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gxjfict.sample.utils.network.Resource;

import io.reactivex.disposables.Disposable;

/**
 * Created by LiuYi on 2019/1/3.
 */
public  class BaseViewModel extends ViewModel {

    protected Disposable mDisposable;
    //
    protected MutableLiveData<Resource> mMutableLiveData = new MutableLiveData<>();
    /**
     * 对RxJava的Disposable进行取消订阅，防止内存泄漏
     */
    protected void unsubscribe() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }


    @Override
    protected void onCleared() {
        unsubscribe();
        super.onCleared();
    }

    public LiveData<Resource> asLiveData() {
        return mMutableLiveData;
    }
}
