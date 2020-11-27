package com.gxjfict.sample.base;

import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author lyw
 * ViewHolder
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mSparseArray = new SparseArray<View>();
    private View mViewItem;
    public int mViewType;

    public RecyclerViewHolder(View itemView, int viewType) {
        super(itemView);
        mViewItem = itemView;
        this.mViewType = viewType;
    }

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mViewItem = itemView;
    }

    public View getView(int viewId){
        View view = mSparseArray.get(viewId);
        if (view==null){
            view = mViewItem.findViewById(viewId);
            mSparseArray.put(viewId,view);
        }
        return  view;
    }

    public View getItemView()
    {
        return mViewItem;
    }

}
