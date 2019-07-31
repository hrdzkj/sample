package com.gxjfict.sample.business.main;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.gxjfict.sample.R;
import com.gxjfict.sample.utils.ToastUtil;
import com.gxjfict.sample.utils.network.HttpConst;
import com.gxjfict.sample.utils.network.NetWork;
import com.gxjfict.sample.widget.LooperTextView;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by LiuYi on 2018/8/29.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    private final int MSG_GET_ALARM_INFO = 0x001;
    private View mRootView;
    private LooperTextView mLooperTextView;



    public static Fragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        return mRootView;
    }

    private void initView() {
        mLooperTextView = mRootView.findViewById(R.id.tv_notice);
        ((TextView) mRootView.findViewById(R.id.include_ivs).findViewById(R.id.textNavigateName)).setText("在线考试");
        ((TextView) mRootView.findViewById(R.id.include_old_vs).findViewById(R.id.textNavigateName)).setText("考试成绩");
        ((TextView) mRootView.findViewById(R.id.include_mobile).findViewById(R.id.textNavigateName)).setText("错题集");
        ((TextView) mRootView.findViewById(R.id.include_door_manager).findViewById(R.id.textNavigateName)).setText("");
        ((TextView) mRootView.findViewById(R.id.include_noman_aircraft).findViewById(R.id.textNavigateName)).setText("");
        ((TextView) mRootView.findViewById(R.id.include_device).findViewById(R.id.textNavigateName)).setText("");
        ((ImageView) mRootView.findViewById(R.id.include_ivs).findViewById(R.id.imageIcon)).setImageResource(R.drawable.vector_online_exam);
        //((ImageView) mRootView.findViewById(R.id.include_old_vs).findViewById(R.id.imageIcon)).setImageResource(R.drawable.vector_camera_navigate_old);
        //((ImageView) mRootView.findViewById(R.id.include_mobile).findViewById(R.id.imageIcon)).setImageResource(R.drawable.vector_video_conference_navigate);
        //((ImageView) mRootView.findViewById(R.id.include_door_manager).findViewById(R.id.imageIcon)).setImageResource(R.drawable.vector_door_manager_vavigate);
        //((ImageView) mRootView.findViewById(R.id.include_noman_aircraft).findViewById(R.id.imageIcon)).setImageResource(R.drawable.vector_aerocraft);
        //((ImageView) mRootView.findViewById(R.id.include_device).findViewById(R.id.imageIcon)).setImageResource(R.drawable.vector_device_statistics_navigate);
        mLooperTextView.setTipList(Arrays.asList("欢迎使用"));
        mRootView.findViewById(R.id.include_ivs).setOnClickListener(this);
        mRootView.findViewById(R.id.include_old_vs).setOnClickListener(this);
        mRootView.findViewById(R.id.include_mobile).setOnClickListener(this);
        mRootView.findViewById(R.id.include_door_manager).setOnClickListener(this);
        mRootView.findViewById(R.id.include_noman_aircraft).setOnClickListener(this);
        mRootView.findViewById(R.id.include_device).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.include_ivs: //智能视频监控
                getCurrentLoginInformations();
                break;

            case R.id.include_old_vs: //原有视频监控"

                break;

            case R.id.include_mobile: //可视电话

                break;

            case R.id.include_door_manager: //智能门禁

                break;

            case R.id.include_noman_aircraft: //无人机

                break;

            case R.id.include_device: //设备运行统计

                break;

            default:
                break;

        }
    }


    @SuppressLint("CheckResult")
    private void getCurrentLoginInformations() {

        NetWork.getInstance()
                .post(HttpConst.GetCurrentLoginInformations, new HashMap<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    ToastUtil.showShort(s);

                }, throwable -> {

                    ToastUtil.showLong("网络异常");
                    throwable.printStackTrace();
                });
    }
}
