package com.example.android12.memoapp;

import android.app.Activity;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android12.memoapp.Commands.ComBack;
import com.example.android12.memoapp.Commands.ComClear;
import com.example.android12.memoapp.Commands.ComDelete;
import com.example.android12.memoapp.Commands.ComMenu;
import com.example.android12.memoapp.Commands.ComSearch;
import com.example.android12.memoapp.Commands.ComShowCurrent;
import com.example.android12.memoapp.Commands.ComShowNext;
import com.example.android12.memoapp.Commands.ComShowPrevious;
import com.example.android12.memoapp.Commands.ComSubmit;
import com.example.android12.memoapp.Commands.MemoCommand;

import java.util.HashMap;

public class MemoController extends Activity{
    private MemoModel mModel;
    private MemoView mView;
    //private MemoDatabase mDatabase;
    private HashMap<Integer, MemoCommand> commands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mModel = new MemoModel(this);
        mView = new MemoView(this, mModel);
        //mDatabase = new MemoDatabase(this);
        commands = new HashMap<>();
        commands.put(R.id.submit_button, new ComSubmit(mModel, mView));
        commands.put(R.id.clear_button, new ComClear(mModel, mView));
        commands.put(R.id.search_button, new ComSearch(mModel, mView));
        commands.put(R.id.previous_button, new ComShowPrevious(mModel, mView));
        commands.put(R.id.current_button, new ComShowCurrent(mModel, mView));
        commands.put(R.id.next_button, new ComShowNext(mModel, mView));
        commands.put(R.id.menu_button, new ComMenu(mModel, mView));
        commands.put(R.id.delete_button, new ComDelete(mModel, mView));
        commands.put(R.id.back_button, new ComBack(mModel, mView));
    }

    @Override
    public void onStart(){
        super.onStart();
        mView.start();
        //mDatabase.start();
        mModel.start();
        mView.setDate(mModel.getCurrentDate());
    }

    public void onClick(View v){
        MemoCommand c = commands.get(v.getId());
        if(null != c){
            c.execute();
        }
    }
}
