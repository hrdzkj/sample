package com.gxjfict.sample.utils.network;

/**
 * Created by LiuYi on 2019-08-03.
 */

/**
 * 下载文件信息
 *
 * @author Kelly
 * @version 1.0.0
 * @filename DownloadInfo.java
 * @time 2018/7/25 14:27
 * @copyright(C) 2018 song
 */
public class DownloadInfo {
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
