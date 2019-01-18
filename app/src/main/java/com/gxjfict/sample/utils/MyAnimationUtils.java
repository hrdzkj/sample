package com.gxjfict.sample.utils;

/**
 * Created by LiuYi on 2019/1/7.
 */

import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

/**
 * @author SoBan
 * @create 2017/2/6 10:02.
 * 调用方式： view.startAnimation(MyAnimationUtils.method(...));
 *
 * 来自 https://blog.csdn.net/qq_30552993/article/details/54948560
 */
public class MyAnimationUtils {

    /**
     * 定义从左侧进入的动画效果
     */
    public static Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(500);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }


    /**
     * 定义从左侧退出的动画效果
     */
    public static Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(500);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }


    /**
     * 定义从右侧进入的动画效果
     */
    public static Animation inFromRightAnimation() {
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    /**
     * 定义从右侧退出时的动画效果
     */
    public static Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(500);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }


    /**
     * 定义从屏幕底部退出的动画效果
     */
    public static Animation outToBottomAnimation() {
        /*
        Animation outToBottom = new TranslateAnimation(0.0f, 0.0f, 0.0f, DensityUtils.getScreenHeight(context));
        outToBottom.setFillAfter(true);
        outToBottom.setDuration(500);
        outToBottom.setRepeatMode(Animation.ZORDER_NORMAL);
        return outToBottom;
        */
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        return slide;
    }


    /**
     * 定义从屏幕底部进入的动画效果
     */
    public static Animation inFromBottomAnimation() {
        /*
        Animation inFromBottom = new TranslateAnimation(0.0f, 0.0f, DensityUtils.getScreenHeight(context), 0.0f);
        inFromBottom.setFillAfter(true);
        inFromBottom.setDuration(500);
        inFromBottom.setRepeatMode(Animation.ZORDER_TOP);
        return inFromBottom;
        */
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        return slide;
    }


}
