package com.example.android12.memoapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemoView {
    private Activity mActivity;
    private MemoModel mModel;
    private TextView wDate;
    private TextView wText;
    private ListView wList;

    public MemoView(Activity activity, MemoModel model){
        mActivity = activity;
        mModel = model;
    }

    public void start(){
        mActivity.setContentView(R.layout.memo_controller);
        wDate = (TextView) mActivity.findViewById(R.id.date_frame);
        wText = (TextView) mActivity.findViewById(R.id.text_frame);
        wList = (ListView) mActivity.findViewById(R.id.list_view);
        setDate(mModel.getCurrentDate());
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

    public void listActivity(){
        mActivity.setContentView(R.layout.memo_list);
        List<Map<String, String>> adaptData = new ArrayList<>();
        adaptData = mModel.getAdapter();
        Log.d("HASH", adaptData.toString());
        SimpleAdapter adapter = new SimpleAdapter(mActivity, adaptData,
                android.R.layout.simple_list_item_2,
                new String[] {"Data", "Text"},
                new int[] {android.R.id.text1,android.R.id.text2});
        wList.setAdapter(adapter);
    }

    public void mainActivity(){
        start();
    }


}
