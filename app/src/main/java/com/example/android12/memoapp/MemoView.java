package com.example.android12.memoapp;

import android.app.Activity;
import android.widget.TextView;

public class MemoView {
    private Activity mActivity; //あとでmを消して小文字にする.
    private MemoModel mModel;
    private TextView wDate;
    private TextView wText;

    public MemoView(Activity activity, MemoModel model){
        mActivity = activity;
        mModel = model;
    }

    public void start(){
        mActivity.setContentView(R.layout.memo_controller);
        wDate = (TextView) mActivity.findViewById(R.id.date_frame);
        wText = (TextView) mActivity.findViewById(R.id.text_frame);
    }

    public String getDate(){
        return wDate.getText().toString();
    }

    public String getText(){
        return wText.getText().toString();
    }

    public void setDate(String date){
        wDate.setText(date);
    }

    public void setText(String text){
        wText.setText(text);
    }

    public void subActivity(){
        mActivity.setContentView(R.layout.subactivity);
    }

    public void mainActivity(){
        start();
    }
}
