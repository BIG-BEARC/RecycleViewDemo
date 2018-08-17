package com.example.yhadmin.recycleviewdemo.db;

/*
 *  @项目名：  RecycleViewDemo 
 *  @包名：    com.example.yhadmin.recycleviewdemo.db
 *  @文件名:   MyDatabaseHelper
 *  @创建者:   YHAdmin
 *  @创建时间:  2018/8/14 10:31
 *  @描述：    TODO
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper
        extends SQLiteOpenHelper
{
    public static final String CREATE_BOOK     = "create table book ("//
            + "id integer primary key autoincrement, "//
            + "author text, "//
            + "price real, "//
            + "pages integer, "//
            + "name text)";//
    //创建新的表category的sql语句
    public static final String CREATE_CATEGORY = "create table Category ("//
            + "id integer primary key autoincrement, "//
            + "category_name text, "//
            + "category_code integer)";


    private Context mContext;

    public MyDatabaseHelper(Context context,
                            String name,
                            SQLiteDatabase.CursorFactory factory,
                            int version)
    {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);

        db.execSQL(CREATE_CATEGORY);//创建新的表category

        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT)
             .show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
