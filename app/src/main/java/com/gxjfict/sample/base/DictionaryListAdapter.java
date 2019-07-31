package com.gxjfict.sample.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.gxjfict.sample.R;
import com.gxjfict.sample.utils.JsonData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuYi on 2019-07-17.
 */
public class DictionaryListAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private InterfaceItemClick.ObjectItemClick mObjectItemClick;
    private LayoutInflater mLayoutInflater;
    private List<JsonData> mDictionaryList = new ArrayList<>();
    private String mKey;


    public DictionaryListAdapter setOnObjectItemClick(InterfaceItemClick.ObjectItemClick itemClick) {
        mObjectItemClick = itemClick;
        return this;
    }

    public DictionaryListAdapter setKey(String key) {
        mKey = key;
        return this;
    }

    public DictionaryListAdapter setDictionaryList(List dictionaryList) {
        mDictionaryList = dictionaryList;
        return this;
    }

    public DictionaryListAdapter(Context mContext) {
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_dictionary_list_adapter, parent, false);
        return new RecyclerViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int pos) {
        JsonData item = mDictionaryList.get(pos);
        ((TextView) recyclerViewHolder.getView(R.id.textName)).setText(item.optString(mKey));
        recyclerViewHolder.getItemView().setOnClickListener(view -> {
            if (mObjectItemClick != null) {
                mObjectItemClick.onItemClick(view, pos, item, this);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDictionaryList.size();
    }
}
