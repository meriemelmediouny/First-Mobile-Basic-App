package com.example.marathonyoussoifia;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "Condidateur.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Condidateurdetail (name TEXT primary key ,Email TEXT , contact TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Condidateurdetail");

    }

    public Boolean insertuserdata(String name, String email, String contact) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("contact", contact);
        long result = DB.insert("Condidateurdetail", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean updateuserdata(String name, String email, String contact) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("contact", contact);
        Cursor cursor = DB.rawQuery("Select * from Condidateurdetail where name =?", new String[]{name});
        if (cursor.getCount() > 0) {


            long result = DB.update("Condidateurdetail", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }


    }
    public boolean deletedata(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Condidateurdetail where name =?", new String[]{name});
        if (cursor.getCount() > 0) {


            long result = DB.delete("Condidateurdetail", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }


    }
    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("Select * from Condidateurdetail", null);

       return  cursor;

    }
}


