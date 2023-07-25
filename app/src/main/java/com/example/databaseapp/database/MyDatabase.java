package com.example.databaseapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabase
{
    public static final String MY_DB="empdDB";
    SQLiteDatabase sdb;
    Cursor cursor;
    MyHelper mh;
    Context myContext;

    //create constructor

    public MyDatabase(Context context)
    {
        this.myContext=context;
        mh=new MyHelper(myContext,MY_DB,null,1);
    }

    //insert values into database

    public void insertEmp(ContentValues cv){

        sdb=mh.getReadableDatabase();
        sdb.insert("employee",null,cv);
    }

    //fetch the values from the database
    public Cursor getData(){

        sdb=mh.getReadableDatabase();
        cursor=sdb.query("employee",null,null,null,null,null,null);
        return cursor;
    }

}
