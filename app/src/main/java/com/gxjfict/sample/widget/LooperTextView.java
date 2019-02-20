package com.gxjfict.sample.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.gxjfict.sample.R;

import java.util.List;

/**
 * Created by Adminis on 2016/8/14.
 */
public class LooperTextView extends FrameLayout {
    private List<String> tipList;
    private int curTipIndex = 0;
    private long lastTimeMillis;
    private static final int ANIM_DELAYED_MILLIONS = 3 * 1000;
    /**
     * 动画持续时长
     */
    private static final int ANIM_DURATION = 1 * 1000;
    private static final String DEFAULT_TEXT_COLOR = "#2F4F4F";
    private final int TEXT_DEFAULT_TEXT_COLOR = 0xff666666;  // 默认字体颜色
    private static final int DEFAULT_TEXT_SIZE = 16;
    //private Drawable head_boy,head_girl;
    private TextView tv_tip_out, tv_tip_in;
    private static final String TIP_PREFIX = "";
    private Animation anim_out, anim_in;
    private float mTextSize;
    private int mTextColor;
    private OnItemClickListener mListener;

    public LooperTextView(Context context) {
        super(context);
        initTipFrame();
        initAnimation();
    }

    public LooperTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LooperTextView);
        mTextColor = typedArray.getColor(R.styleable.LooperTextView_loopertextColor, TEXT_DEFAULT_TEXT_COLOR);
        mTextSize = typedArray.getDimension(R.styleable.LooperTextView_loopertextSize, 0);
        typedArray.recycle();
        initTipFrame();
        initAnimation();
    }

    public LooperTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LooperTextView);
        mTextColor = typedArray.getColor(R.styleable.LooperTextView_loopertextColor, TEXT_DEFAULT_TEXT_COLOR);
        mTextSize = typedArray.getDimension(R.styleable.LooperTextView_loopertextSize, mTextSize);
        typedArray.recycle();
        initTipFrame();
        initAnimation();
    }

    private void initTipFrame() {
//        head_boy = loadDrawable(R.mipmap.user_head_boy);
//        head_girl = loadDrawable(R.mipmap.user_head_girl);
        tv_tip_out = newTextView();
        tv_tip_in = newTextView();
        addView(tv_tip_in);
        addView(tv_tip_out);
    }

    private TextView newTextView() {
        TextView textView = new TextView(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.CENTER_VERTICAL);
        textView.setLayoutParams(lp);
        textView.setCompoundDrawablePadding(10);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setTextColor(mTextColor);
        textView.setTextSize(15);

        return textView;
    }

    /**
     * 将资源图片转换为Drawable对象
     *
     * @param ResId
     * @return
     */
    private Drawable loadDrawable(int ResId) {
        Drawable drawable = getResources().getDrawable(ResId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth() - 10, drawable.getMinimumHeight() - 10);
        return drawable;
    }

    private void initAnimation() {
        anim_out = newAnimation(0, -1);
        anim_in = newAnimation(1, 0);
        anim_in.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                updateTipAndPlayAnimationWithCheck();
            }
        });
    }

    private Animation newAnimation(float fromYValue, float toYValue) {
        Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, fromYValue, Animation.RELATIVE_TO_SELF, toYValue);
        anim.setDuration(ANIM_DURATION);
        anim.setStartOffset(ANIM_DELAYED_MILLIONS);
        anim.setInterpolator(new DecelerateInterpolator());
        return anim;
    }

    private void updateTipAndPlayAnimationWithCheck() {
        if (System.currentTimeMillis() - lastTimeMillis < 1000) {
            return;
        }

        lastTimeMillis = System.currentTimeMillis();
        updateTipAndPlayAnimation();
    }

    private void updateTipAndPlayAnimation() {

        if (curTipIndex % 2 == 0) {
            updateTip(tv_tip_out);
            tv_tip_in.startAnimation(anim_out);
            tv_tip_out.startAnimation(anim_in);
            this.bringChildToFront(tv_tip_in);
        } else {
            updateTip(tv_tip_in);
            tv_tip_out.startAnimation(anim_out);
            tv_tip_in.startAnimation(anim_in);
            this.bringChildToFront(tv_tip_out);
        }
    }

    private void updateTip(TextView tipView) {
        String tip = getNextTip();
        if (!TextUtils.isEmpty(tip)) {
            tipView.setText(tip + TIP_PREFIX);
            tipView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView textView = (TextView) v;
                    int cout=curTipIndex;
                    cout-=2;
                    int  position=cout%tipList.size();
                    if(mListener != null){
                        mListener.onItemClick(textView,position);
                    }
                }
            });
        }
    }



    /**
     * 获取下一条消息
     *
     * @return
     */
    private String getNextTip() {
        if (isListEmpty(tipList)) return null;
        return tipList.get(curTipIndex++ % tipList.size());
    }

    public static boolean isListEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public void setTipList(List<String> tipList) {
        this.tipList = tipList;
        curTipIndex = 0;
        updateTip(tv_tip_out);
        updateTipAndPlayAnimation();

    }
    /**
     * 点击的回调。TextView是被点中的公告。
     */
    public interface OnItemClickListener {
        void onItemClick(TextView view, int position);
    }

    /**
     * 设置条目点击侦听
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

}