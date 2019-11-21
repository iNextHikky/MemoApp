package com.example.android12.memoapp.Commands;

import com.example.android12.memoapp.MemoModel;
import com.example.android12.memoapp.MemoView;

public class ComList extends MemoCommand{
    private MemoModel mModel;
    private MemoView mView;

    public ComList(MemoModel model, MemoView view){
        mModel = model;
        mView = view;
    }

    public void execute() {
        mView.listActivity();
    }
}
