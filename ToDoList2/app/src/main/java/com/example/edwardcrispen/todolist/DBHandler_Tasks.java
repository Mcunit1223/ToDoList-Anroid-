package com.example.edwardcrispen.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Edward Crispen on 10/22/2016.
 */

public class DBHandler_Tasks extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "List_database.db";
    public static final String TABLE_LISTS = "Lists";
    public static final String COLUMN_NAME = "name";

    public DBHandler_Tasks(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_LISTS + "( " +
                COLUMN_NAME + " TEXT "  +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_LISTS);
        onCreate(db);
    }

    public void addList(String name){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_LISTS, null, values);
        db.close();
    }

    public void deleteList(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_LISTS +
                " WHERE " + "\"" + COLUMN_NAME + "=\"" + name + "\";");
    }

    public ArrayList<List> toArrayList(){
        ArrayList<List> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_LISTS + " WHERE 1";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_NAME)) != null){
                list.add(new List(c.getString(c.getColumnIndex(COLUMN_NAME))));
                c.moveToNext();
            }
        }
        db.close();
        return list;
    }
}
