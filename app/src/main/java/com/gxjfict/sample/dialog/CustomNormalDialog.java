package com.gxjfict.sample.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gxjfict.sample.R;


public class CustomNormalDialog extends Dialog {

    public CustomNormalDialog(Context context) {
        super(context);
    }

    public CustomNormalDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CustomNormalDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final CustomNormalDialog dialog = new CustomNormalDialog(context, R.style.Dialog);
            View layout = inflater.inflate(R.layout.custom_normal_dialog_layout, null);
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            // set the dialog title
            ((TextView) layout.findViewById(R.id.title)).setText(title);
            // set the confirm button
            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.positiveButton)).setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.positiveButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }

                if (mPositiveBackground!=-1){
                    layout.findViewById(R.id.positiveButton).setBackgroundColor(mPositiveBackground);
                }
                if (mPositiveTextColor!=-1){
                    System.out.println(mPositiveTextColor+" = mPositiveTextColor");
                    ((TextView) layout.findViewById(R.id.positiveButton)).setTextColor(mPositiveTextColor);
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(View.GONE);
                layout.findViewById(R.id.line).setVisibility(View.GONE);


            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);

                if (negativeButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.negativeButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }

                if (mNegativeBackground!=-1){
                    layout.findViewById(R.id.negativeButton).setBackgroundColor(mNegativeBackground);
                }
                if (mNegativeTextColor!=-1){
                    ((TextView) layout.findViewById(R.id.negativeButton)).setTextColor(mNegativeTextColor);
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(View.GONE);
                layout.findViewById(R.id.line).setVisibility(View.GONE);
            }
            // set the content message
            if (message != null) {
                ((TextView) layout.findViewById(R.id.message)).setText(message);
            }else if (messageSpanned!=null){
                ((TextView) layout.findViewById(R.id.message)).setText(messageSpanned);
            }else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((LinearLayout) layout.findViewById(R.id.content))
                        .removeAllViews();
                ((LinearLayout) layout.findViewById(R.id.content)).addView(contentView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            }
            if(TextUtils.isEmpty(this.title)){
                ((TextView) layout.findViewById(R.id.title)).setVisibility(View.GONE);
            }
            dialog.setContentView(layout);
            dialog.setCanceledOnTouchOutside(mIsCanceledOnTouchOutside);
            return dialog;
        }

        Spanned messageSpanned;
        public Builder setMessage(Spanned message) {
            this.messageSpanned = message;
            return this;
        }

        int mNegativeBackground=-1,mPositiveBackground=-1;
        int mNegativeTextColor=-1,mPositiveTextColor=-1;

        public void setNegativeBackground(int bg){
            mNegativeBackground = bg;
        }
        public void setPositiveBackground(int bg){
            mPositiveBackground = bg;
        }
        public void setNegativeTextColor(int color){
            mNegativeTextColor=color;
        }
        public void setPositiveTextColor(int color){
            mPositiveTextColor =color;
        }

        boolean mIsCanceledOnTouchOutside=true;
        public void setCanceledOnTouchOutside(boolean b){
            mIsCanceledOnTouchOutside=b;
        }
    }
}
