package com.gxjfict.sample.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by lenvov on 2016/12/3.
 */

public interface InterfaceItemClick {
    interface ObjectItemClick{
        void onItemClick(View view, int position, Object object, RecyclerView.Adapter adapter);
    }

}
