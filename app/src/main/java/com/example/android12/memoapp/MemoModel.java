package com.example.android12.memoapp;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemoModel{
    private Context mContext;
    private MemoDatabase database;
    private List<String> mDateResults = new ArrayList<>();
    private List<String> mTextResults = new ArrayList<>();
    private int mIndex = 0;

    public MemoModel(Context context){
        mContext = context;
        database = new MemoDatabase(mContext);
    }

    public void start(){
        database.start();
        database.copyAllEntries(mDateResults, mTextResults);
        //Log.d("copySuccess", mDateResults.get(0));
    }

    public void submit(String date, String text){
        database.submit(date, text);
    }

    public void search(String date, String text) {
        database.search(date, text, mDateResults, mTextResults);
        if(mDateResults != null&& mTextResults != null){
            mIndex = 0;
        }else
            return;
        //Log.d("search", "mIdx"+ mDateResults.get(0));
    }

    public String getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }

    public String getFocusedDate(){
        if (mIndex >= 0 && mIndex < mDateResults.size())
            return mDateResults.get(mIndex);
        else
            return null;
    }

    public String getFocusedText(){
        if (mIndex >= 0 && mIndex < mTextResults.size())
            return mTextResults.get(mIndex);
        else
            return null;
    }

    public boolean movePrevious() {
        if (mIndex > 0 && mIndex < mTextResults.size()) {
            mIndex -= 1;
            return true;
        }else
            return false;
    }

    public boolean moveCurrent() {
        int sz = mTextResults.size();
        if(sz < 0){
            mIndex = -1;
            return false;
        }else {
            mIndex = sz - 1;
            return true;
        }
    }

    public boolean deleteCurrent(){
        if(mDateResults.get(mIndex) == null)
            return false;
        if(mTextResults.get(mIndex) == null)
            return false;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("削除ログ");
        builder.setMessage("'" + mTextResults.get(mIndex) + "'を削除します");
        AlertDialog dialog = builder.create();
        dialog.show();
        mDateResults.set(mIndex, null);
        mTextResults.set(mIndex, null);
        database.deleteDateTexts(mIndex);
        return true;
    }

    public boolean moveNext(){
        if (mIndex >= 0 && mIndex < mTextResults.size() - 1) {
            mIndex += 1;
            return true;
        }else
            return false;
    }

    public void resetClear(){
        database.copyAllEntries(mDateResults, mTextResults);
    }
}