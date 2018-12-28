package com.gxjfict.sample.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.gxjfict.sample.R;


public class LoadingDialog extends Dialog {

	private Context mContext;

	public LoadingDialog(Context context) {
		super(context);
		this.mContext = context;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		setContentView(R.layout.dialog_loading);
		setCanceledOnTouchOutside(false);
	}

	public  void cancelDialog() {
		dismiss();
	}

	public void showDialog() {
		if(!this.isShowing()){
			this.show();
		}
	}

}
