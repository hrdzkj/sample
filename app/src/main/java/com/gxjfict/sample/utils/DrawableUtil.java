package com.gxjfict.sample.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;

/**
 * Created by lenvov on 2017/2/15.
 */

public class DrawableUtil {

    //点击改变背景颜色的selector
    public   static StateListDrawable selectorBackgroundColor (Context context, int normal, int pressed) {
        ;
        StateListDrawable states = new StateListDrawable ();
        states.addState (new int[]{ android.R.attr.state_pressed }, new ColorDrawable(ContextCompat.getColor(context,pressed)));
        states.addState (new int[]{ }, new ColorDrawable(ContextCompat.getColor(context,normal)));
        return states;
    }

    //Text selector
    public static   ColorStateList selectorText(Context context, int normal,int pressed) {
        ColorStateList colorStates = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_pressed},
                        new int[]{}
                },
                new int[]{
                        ContextCompat.getColor(context,pressed),
                        ContextCompat.getColor(context,normal)});


        return colorStates;
    }

    //改变背景shape的selector
    public static StateListDrawable selectorBackgroundDrawable (Drawable normalDraw, Drawable pressedDraw) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDraw);
        stateListDrawable.addState(new int[]{}, normalDraw);
        return stateListDrawable;
    }

   //生产shape
    public static GradientDrawable getGradientDrawable(Context context, int radius, @DrawableRes int fillColor, int width, @DrawableRes int strokeColor) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(radius);
        gradientDrawable.setColor(ContextCompat.getColor(context,fillColor));
        gradientDrawable.setStroke(width, ContextCompat.getColor(context,strokeColor));

        return gradientDrawable;
    }

}
