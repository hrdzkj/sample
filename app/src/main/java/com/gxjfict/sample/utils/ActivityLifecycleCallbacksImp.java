package com.gxjfict.sample.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by LiuYi on 2018/8/29.
 */

public class ActivityLifecycleCallbacksImp implements Application.ActivityLifecycleCallbacks{
    private static List<SoftReference<Activity>> mActivityList = new LinkedList<>();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
       if (activity!=null){
           addActivity(activity);
       }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        if (activity!=null){
            removeActivity(activity);
        }
    }




    // 添加Activity到容器中
    private synchronized static void addActivity(Activity activity) {
        //移除原有的
        for (int i = mActivityList.size() - 1; i >= 0; i--) {
            SoftReference<Activity> softReferenceActivity = mActivityList.get(i);
            Activity a = softReferenceActivity.get();
            if (a == null || a == activity) {
                mActivityList.remove(softReferenceActivity);
            }
        }

        if (activity!=null) {
            mActivityList.add(new SoftReference<>(activity));
        }
    }

    /**
     * 移除activity,代替finish
     */
    private synchronized static void removeActivity(Activity activity) {
        for (int i = mActivityList.size() - 1; i >= 0; i--) {
            SoftReference<Activity> softReferenceActivity = mActivityList.get(i);
            Activity a = softReferenceActivity.get();
            if (a == activity) {
                mActivityList.remove(softReferenceActivity);
            }
        }
    }

    /**
     * 判断Activity是否运行
     * @param activityClassName String
     * @return boolean
     */
    public synchronized static boolean isActivityRunning(String activityClassName) {
        boolean isFind = false;
        for (int i = mActivityList.size() - 1; i >= 0; i-- ) {
            SoftReference<Activity> softReferenceActivity = mActivityList.get(i);
            Activity activity = softReferenceActivity.get();
            if (activity==null){
                mActivityList.remove(softReferenceActivity);
                continue;
            }

            if (activity.getClass().getName().equals(activityClassName)) {
                isFind = true;
                break;
            }
        }
        return isFind;
    }


}
