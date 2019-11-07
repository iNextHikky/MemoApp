package com.example.android12.memoapp.Commands;

import android.util.Log;

import com.example.android12.memoapp.MemoModel;
import com.example.android12.memoapp.MemoView;

import java.util.ArrayList;
import java.util.List;

public class ComSearch extends MemoCommand{
    private MemoModel mModel;
    private MemoView mView;

    public ComSearch(MemoModel model, MemoView view){
        mModel = model;
        mView = view;
    }

    public void execute(){
        String date = mView.getDate();
        String text = mView.getText();
        mModel.search(date, text);
        mView.setDate(mModel.getFocusedDate());
        mView.setText(mModel.getFocusedText());
    }
}
