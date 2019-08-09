package com.gxjfict.sample.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.gxjfict.sample.R;
import com.gxjfict.sample.utils.ToastUtil;
import com.gxjfict.sample.utils.network.NetWork;


import java.io.File;


public class UpdateVersionDialog extends Dialog {

    public UpdateVersionDialog(Context context) {
        super(context);
    }

    public UpdateVersionDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private NumberProgressBar mUpdateProgressBar;

        private String mVersionName;
        private String mUpdateAppUrl;
        public Builder(Context context) {
            this.context = context;
        }




        public Builder setVersionName(String versionName){
           mVersionName = versionName;
           return this;
        }

        public Builder setUpdateAppUrl(String url){
            mUpdateAppUrl = url;
            return  this;
        }

        public UpdateVersionDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final UpdateVersionDialog dialog = new UpdateVersionDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.appupdate_popup, null);
            mUpdateProgressBar = layout.findViewById(R.id.UpdateProgressBar);
            TextView updateMessage = layout.findViewById(R.id.UpdateMessage);
            updateMessage.setText("发现V" + mVersionName + "版本,是否更新？");

            dialog.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            layout.findViewById(R.id.btnBack).setOnClickListener(view -> dialog.dismiss());
            layout.findViewById(R.id.btnConfirm).setOnClickListener(v->{
                updateMessage.setVisibility(View.INVISIBLE);
                mUpdateProgressBar.setVisibility(View.VISIBLE);
                String filePath = Environment.getExternalStorageDirectory().getPath()+ File.separator+"Download"+File.separator;
                String fileName = "myDown.apk";
                NetWork.getInstance().download(mUpdateAppUrl, new NetWork.FileCallback(filePath,fileName){
                    @Override
                    public void onFinish() {
                        dialog.dismiss();
                        ToastUtil.showShort("下载成功");
                        //SimpleUtils.openFile(context, new File(filePath,fileName));
                    }

                    @Override
                    public void onProgress(int progress) {
                        mUpdateProgressBar.setProgress(Integer.valueOf(progress));
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                        dialog.dismiss();
                        ToastUtil.showShort("下载失败");
                    }
                });
            });
            dialog.setCanceledOnTouchOutside(false);
            dialog.setContentView(layout);
            return dialog;
        }


    }


}
