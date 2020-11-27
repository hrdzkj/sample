package com.gxjfict.sample.business.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.AppUtils;
import com.gxjfict.sample.R;
import com.gxjfict.sample.business.login.LoginActivity;
import com.gxjfict.sample.dialog.CustomNormalDialog;
import com.orhanobut.hawk.Hawk;


/**
 * Created by LiuYi on 2018/8/29.
 */

public class MeFragment extends Fragment implements View.OnClickListener {
    private View mRootView;


    public static MeFragment newInstance() {
        return new MeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_me, container, false);
        initView();
        return mRootView;
    }

    private void initView() {
        ((TextView) mRootView.findViewById(R.id.textVersion)).setText("版本V" + AppUtils.getAppVersionName());
        mRootView.findViewById(R.id.layoutTE).setOnClickListener(this::onClick);
    }

    /**
     * 显示退出确定对话框
     */
    private void showLoginOutConfirmDialog() {
        CustomNormalDialog.Builder builder = new CustomNormalDialog.Builder(getContext());
        builder.setMessage("您确定要退出登录吗？")
                .setPositiveButton("确定", (dialog, which) -> {
                    dialog.dismiss();
                    Hawk.deleteAll();
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    getActivity().finish();
                })
                .setNegativeButton("取消", (dialog, which) -> {
                    dialog.dismiss();
                }).create().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layoutTE:
                showLoginOutConfirmDialog();
                break;

            default:
                break;
        }
    }


}
