package com.example.databaseapp.database;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyHelper extends SQLiteOpenHelper {


    public MyHelper(@Nullable Context context, @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //lets execute SQL query to create table
        String query="CREATE TABLE employee(_id INTEGER PRIMARY KEY,name TEXT,"+"address TEXT ,salary INTEGER);";
        db.execSQL(query);
        Log.i("Employee DB","Database created");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
