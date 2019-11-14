package com.example.android12.memoapp.Commands;

import com.example.android12.memoapp.MemoModel;
import com.example.android12.memoapp.MemoView;

public class ComDelete extends MemoCommand{
    private MemoModel mModel;
    private MemoView mView;

    public ComDelete(MemoModel model, MemoView view){
        mModel = model;
        mView = view;
    }

    public void execute(){
        mModel.deleteCurrent();
    }
}
