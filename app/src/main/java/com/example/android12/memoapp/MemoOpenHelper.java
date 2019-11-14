package com.example.android12.memoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLData;

public class MemoOpenHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MemoDB.db";
    private static final String TABLE_NAME = "memodb";
    private static final String _DATE = "_date";
    private static final String _TEXT = "_text";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " ( " + _DATE + " DATE PRIMARY KEY " + _TEXT + " TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    MemoOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void saveDate(SQLiteDatabase db, String date, String text){
        ContentValues values = new ContentValues();
        values.put("date", date);
        values.put("text", text);
        db.insert("memodb", null, values);
    }

    public String readDate(SQLiteDatabase db){
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[] {"date", "text"},
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++){
            sbuilder.append(cursor.getString(0));
            sbuilder.append(": ");
            sbuilder.append(cursor.getString(1));
            sbuilder.append("¥n");
            cursor.moveToNext();
        }

        cursor.close();

        return sbuilder.toString();
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int  oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }
}
