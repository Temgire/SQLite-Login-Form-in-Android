package com.example.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME="Login.db";

    public DBHelper( Context context ) {
        super(context, "Login.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table kundan(username TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists kundan");
    }
    public boolean insertData(String username,String password){
        SQLiteDatabase MyDB =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result=MyDB.insert("kundan",null,contentValues);
        if(result==-1) return false;
        else
            return  true;

    }
    public boolean checkusername(String username){
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor= MyDB.rawQuery("Select * from kundan where username= ?",new String[] {username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;

    }
    public  boolean checkusernamepassword(String username,String password){
        SQLiteDatabase MyDb=this.getWritableDatabase();
        Cursor cursor= MyDb.rawQuery("Select * from kundan where username = ? and password = ?",new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
