package com.example.android12.memoapp.Commands;

import com.example.android12.memoapp.MemoModel;
import com.example.android12.memoapp.MemoView;

public class ComMenu extends MemoCommand{
    private MemoModel mModel;
    private MemoView mView;

    public ComMenu(MemoModel model, MemoView view){
        mModel = model;
        mView = view;
    }

    public void execute(){
        mView.subActivity();
    }
}
