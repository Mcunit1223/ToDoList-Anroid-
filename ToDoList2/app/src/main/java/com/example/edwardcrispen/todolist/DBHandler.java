package com.example.edwardcrispen.todolist;

import android.database.Cursor;
import android.database.sqlite.*;
import android.content.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "List_database.db";
    public static final String TABLE_LISTS = "Lists";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COMPLETE = "complete";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_LISTS + "( " +
                COLUMN_NAME + " TEXT "  +
                COLUMN_COMPLETE + " BOOLEAN "
                + ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_LISTS);
        onCreate(db);
    }

    public void addList(List list){
        ContentValues values = new ContentValues();
        for (Task t : list.toArrayList()) {
            values.put(COLUMN_NAME, t.getName());
            values.put(COLUMN_COMPLETE, t.isComplete());
        }
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_LISTS, null, values);
        db.close();
    }

    public void deleteTask(Task task){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_LISTS +
        " WHERE " + "\"" + COLUMN_NAME + "=\"" + task.getName() + "\";");
    }

    public ArrayList<Task> toArrayList(){
        ArrayList<Task> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_LISTS;
        Cursor c = db.rawQuery(query, null);
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_NAME)) != null){
                Task task = new Task();
                task.setName(c.getString(c.getColumnIndex(COLUMN_NAME)));
                Boolean b = Boolean.getBoolean(c.getString(c.getColumnIndex(COLUMN_COMPLETE)));
                task.setComplete(b);
                list.add(task);
            }
        }
        db.close();
        return list;
    }
}
