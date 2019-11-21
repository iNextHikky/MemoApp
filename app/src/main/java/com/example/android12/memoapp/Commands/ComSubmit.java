package com.example.android12.memoapp.Commands;

import com.example.android12.memoapp.MemoModel;
import com.example.android12.memoapp.MemoView;

public class ComSubmit extends MemoCommand {
    private MemoModel mModel;
    private MemoView mView;

    public ComSubmit(MemoModel model, MemoView view){
        mModel = model;
        mView = view;
    }

    public void execute(){
        String date = mView.getDate();
        String text = mView.getText();
        mModel.submit(date, text);
        mView.setText("");
        mView.setDate(mModel.getCurrentDate());
        mModel.resetClear();
    }
}
