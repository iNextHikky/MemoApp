package com.example.android12.memoapp.Commands;

import com.example.android12.memoapp.MemoModel;
import com.example.android12.memoapp.MemoView;

public class ComShowCurrent extends MemoCommand{
    private MemoModel mModel;
    private MemoView mView;

    public ComShowCurrent(MemoModel model, MemoView view){
        mModel = model;
        mView = view;
    }

    public void execute(){
        /*
        mView.setDate(mModel.getCurrentDate());
        mView.setText("");
        */
        if(mModel.moveCurrent()){
            mView.setText(mModel.getFocusedText());
            mView.setDate(mModel.getFocusedDate());
        }else
            return;
    }
}
