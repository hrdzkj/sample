package com.gxjfict.sample.utils.network;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.gxjfict.sample.utils.network.Status.ERROR;
import static com.gxjfict.sample.utils.network.Status.LOADING;
import static com.gxjfict.sample.utils.network.Status.SUCCESS;

/**
 * Created by LiuYi on 2019/1/3.
 * 参考https://developer.android.com/jetpack/docs/guide#addendum
 */
//a generic class that describes a data with a status
public class Resource<T> {
    @NonNull private final Status status;
    @Nullable private final T data;
    @Nullable private final String message;

    @NonNull
    public Status getStatus() {
        return status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public String getMessage() {
        return message;
    }


    private Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }
}
