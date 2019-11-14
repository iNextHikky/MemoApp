package com.example.android12.memoapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemoModel {
    private Context mContext;
    /*
    private MemoDatabase database;
    private List<String> mDateResults = new ArrayList<>();
    private List<String> mTextResults = new ArrayList<>();
    */
    private SQLiteDatabase mDB;
    //private int mIndex = 0;

    public MemoModel(Context context){
        mContext = context;
        //database = new MemoDatabase(mContext);
    }

    public void start(){
        //database.start();
        //database.copyAllEntries(mDateResults, mTextResults);
        //Log.d("copySuccess", mDateResults.get(0));
        if(mDB == null){
            mDB = mContext.openOrCreateDatabase("my_database_name", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        }
    }

    public void stop(){
        if(mDB != null) {
            mDB.close();
            mDB = null;
        }
    }

    public void tableCreate(){
        mDB.execSQL("CREATE TABLE IF NOT EXISTS my_table (date DATE, text TEXT);");
    }

    public void tableDelete(){
        mDB.execSQL("DROP TABLE IF EXISTS my_table;");
    }

    public void recordInsert(String date, String text){
        mDB.execSQL("INSERT INTO my_table VALUES('"+ date +"', '"+ text +"')");
    }

    public void search(String text){
        Cursor c = mDB.rawQuery("SELECT * FROM my_table WHERE text == " + text + ";", null);
        while (c.moveToNext()){
            show(c.getString(0), c.getString(1));
        }
        c.close();
    }

    public void show(String date, String text){

    }

    /*
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

    public void clearResults(){
        mDateResults.clear();
        mTextResults.clear();
    }
*/
    public String getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }
/*
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

    public int substractDate(String t1, String t2){
     d1, d2;
    try{
        d1 = DateFormat.parse(t1);
        d2 = DateFormat.parse(t2);
    }catch (ParseException e){
        d1 = null;
        d2 = null;
    }if (d1 == null|| d2 == null)
    return 0;
}

    public boolean moveCurrent() {
        /*
        int sz = mTextResults.size();
        if (sz <= 0) {
            mIndex = -1;
            return false;
        } else {
            int idx, jdx;
            String now = getCurrentDate();
            int min = substractDate(mDateResults.get(0), now);
            jdx = 0;
            for (idx = 1; idx < sz; idx++) {
                int diff = substractDate(mDateResults.get(idx), now);
                if (diff < min) {
                    jdx = idx;
                    min = diff;
                }
            }
            mIndex = jdx;
            return true;
        }

        int sz = mTextResults.size();
        if(sz < 0){
            mIndex = -1;
            return false;
        }else {
            mIndex = sz - 1;
            return true;
        }
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
    */
}