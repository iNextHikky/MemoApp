package com.example.android12.memoapp.Commands;

import com.example.android12.memoapp.MemoModel;
import com.example.android12.memoapp.MemoView;

public class ComFileDelete extends MemoCommand{
    private MemoModel mModel;
    private MemoView mView;

    public ComFileDelete(MemoModel model, MemoView view){
        mModel = model;
        mView = view;
    }

    public void execute(){
        mModel.deleteFile();
        mModel.start();
        mView.mainActivity();
    }
}
