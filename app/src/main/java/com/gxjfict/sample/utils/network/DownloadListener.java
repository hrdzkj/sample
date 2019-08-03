package com.gxjfict.sample.utils.network;

import java.io.File;

/**
 * Created by liuyang on 2016/12/20.
 */

public interface DownloadListener {
    void onFinish();

    void onProgress(int progress);

    void onFailed(Throwable throwable);
}