package com.example.android12.memoapp.Commands;

import com.example.android12.memoapp.MemoModel;
import com.example.android12.memoapp.MemoView;

public class ComShowNext extends MemoCommand{
    private MemoModel mModel;
    private MemoView mView;

    public ComShowNext(MemoModel model, MemoView view){
        mModel = model;
        mView = view;
    }

    public void execute(){
        if(mModel.moveNext()) {
            mView.setText(mModel.getFocusedText());
            mView.setDate(mModel.getFocusedDate());
        }else
            return;
    }
}
