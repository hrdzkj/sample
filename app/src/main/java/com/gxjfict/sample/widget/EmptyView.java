package com.gxjfict.sample.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gxjfict.sample.R;


/**
 * Created by LiuYi on 2018/10/12.
 */
public class EmptyView extends RelativeLayout {
   //无数据   R.drawable.emptybox 无网络R.drawable.ic_no_network
    private ImageView mImageEmpty;
    private TextView mTextEmpty;


    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化View
     *
     * @param context
     */
    private void init(Context context) {
        View.inflate(context, R.layout.widget_emptyview, this);
        mImageEmpty = findViewById(R.id.imageEmpty);
        mTextEmpty = findViewById(R.id.textShowText);
    }


    public void setEmptyImage(@DrawableRes int resId) {

        mImageEmpty.setImageResource(resId);

    }

    public void setEmptyShowText(String showText) {
        mTextEmpty.setText(showText);
    }


}