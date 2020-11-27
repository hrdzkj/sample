package com.gxjfict.sample.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by lenvov on 2016/12/3.
 */

public interface InterfaceItemClick {
    interface ObjectItemClick{
        void onItemClick(View view, int position, Object object, RecyclerView.Adapter adapter);
    }

}
