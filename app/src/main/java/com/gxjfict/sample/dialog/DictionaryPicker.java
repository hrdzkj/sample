package com.gxjfict.sample.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.gxjfict.sample.utils.JsonData;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuYi on 2019-07-17.
 */
public class DictionaryPicker {
    private OptionsPickerView<Object> builder;

    public void show() {
        if (builder != null) {
            builder.show();
        }
    }

    // 自定义接口，直接传选中的json
    public interface OnSelectDictionaryListener {
        void onSelect(JsonData item, int options1);
    }

    public static class Builder {
        private Context context;
        private List<JsonData> mOptionList1 = null;

        private String nameKey1;


        private OnSelectDictionaryListener mOnSelect;


        public Builder setOptionList1(List<JsonData> list, String nameKey) {
            mOptionList1 = list;
            nameKey1 = nameKey;
            return this;
        }

        public Builder setOnSelectListener(OnSelectDictionaryListener l) {
            mOnSelect = l;
            return this;
        }

        public Builder(Context context) {
            this.context = context;
        }


        public DictionaryPicker build() {
            DictionaryPicker dictionaryPicker = new DictionaryPicker();
            List<Object> optList1 = new ArrayList<>();
            if (mOptionList1 != null && !TextUtils.isEmpty(nameKey1)) {
                for (JsonData item : mOptionList1) {
                    optList1.add(item.optString(nameKey1));
                }
            }
            dictionaryPicker.builder = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    if (mOnSelect != null) {
                        mOnSelect.onSelect(mOptionList1.get(options1), options1);
                    }
                }
            }).build();
            dictionaryPicker.builder.setPicker(optList1);
            return dictionaryPicker;
        }
    }


}
